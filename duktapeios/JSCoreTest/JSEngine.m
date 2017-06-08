  //
//  JSEngine.m
//  JSCoreTest
//
//  Created by furture on 16/6/23.
//  Copyright © 2016年 Evgenii Neumerzhitckii. All rights reserved.
//

#import "JSEngine.h"
#import "duk_config.h"
#import "duktape.h"
#import "refs.h"
#import "JSRef.h"


#define OC_OBJECT_MARK     "__oc"

#define JS_REF_MARK     "__js"

#define PROTOTYPE_KEY     "prototype"

#define PROTOTYPE_CLASS_NAME_KEY     "__cls"

#define JS_ENGINE_MARK    "__engine"


#define  OC_METHOD_MARK    "__c"


@interface JSEngine (){
}

@property (nonatomic) duk_context *ctx;


@end


@implementation JSEngine

void duk_js_fatal_function(duk_context *ctx, duk_errcode_t code, const char *msg){
    NSLog(@"ScriptEngine jni fatal error code %d  msg %s", code, msg);
    abort();
}





static duk_ret_t duk_js_log_print(duk_context *ctx) {
    int n = duk_get_top(ctx);
    switch (n) {
        case 1:
            printf("ScriptEngine %s", duk_safe_to_string(ctx, 0));
            break;
        case 2:
             printf("ScriptEngine %s %s", duk_safe_to_string(ctx, 0),
                 duk_safe_to_string(ctx, 1));
            break;
        case 3:
            printf("ScriptEngine %s %s %s", duk_safe_to_string(ctx, 0),
                 duk_safe_to_string(ctx, 1), duk_safe_to_string(ctx, 2));
            break;
        case 4: {
              printf("ScriptEngine %s %s %s %s", duk_safe_to_string(ctx, 0),
                 duk_safe_to_string(ctx, 1), duk_safe_to_string(ctx, 2),
                 duk_safe_to_string(ctx, 3));
            }
            break;
        case 5: {
               printf("ScriptEngine %s %s %s %s %s", duk_safe_to_string(ctx, 0),
                  duk_safe_to_string(ctx, 1), duk_safe_to_string(ctx, 2),
                  duk_safe_to_string(ctx, 3), duk_safe_to_string(ctx, 4));
             }
            break;
        case 6: {
            printf("ScriptEngine %s %s %s %s %s %s", duk_safe_to_string(ctx, 0),
                   duk_safe_to_string(ctx, 1), duk_safe_to_string(ctx, 2),
                   duk_safe_to_string(ctx, 3), duk_safe_to_string(ctx, 4), duk_safe_to_string(ctx, 5));
             }
            break;
        case 0: {
            printf("ScriptEngine \n");
        }
            break;
        default: {
            printf("ScriptEngine too many args %d to print, javascript print only support max  6 args\n", n);
        }
            break;
    }
    return 0;
}

JSEngine* get_engine_from_context(duk_context *ctx){
    duk_push_global_object(ctx);
    if(duk_get_prop_string(ctx, -1, JS_ENGINE_MARK)){
        JSEngine* engine =  (__bridge JSEngine *)(duk_to_pointer(ctx, -1));
        duk_pop_2(ctx);
        return engine;
    }
    duk_pop_2(ctx);
    return NULL;
}

static const char * duk_js_error_to_string(duk_context *ctx, int index){
    if(duk_get_prop_string(ctx, index, "lineNumber")){
        int lineNumber = duk_get_int(ctx, -1);
        duk_pop(ctx);
        duk_push_sprintf(ctx, " %s  at javascript source %d line", duk_to_string(ctx, index), lineNumber);
        if(index < 0){
            duk_replace(ctx, index - 1);
        }else{
            duk_replace(ctx, index);
        }
        return duk_to_string(ctx,index);
    }
    duk_pop(ctx);
    return duk_to_string(ctx, index);
}


static int oc_object_to_string(duk_context *ctx) {
    duk_push_this(ctx);
    if(duk_get_prop_string(ctx, -1, OC_OBJECT_MARK)){
         NSLog(@"ScriptEngine oc_object_to_string enter oc object ");
        void*  ref = duk_to_pointer(ctx, -1);
        if(ref == NULL){
            duk_push_null(ctx);
            return 1;
        }
        id refId = (__bridge id)(ref);
        NSString* value = [refId description];
        const char* src =  [value UTF8String];
        duk_push_string(ctx, src);
        NSLog(@"ScriptEngine oc_object_to_string src %s", src );
        return 1;
    }else{
        duk_dup(ctx, -1);
        duk_to_string(ctx, -1);
        return 1;
    }
    return 0;
}

static int empty_oc_object_finalizer(duk_context *ctx) {
    if(duk_get_prop_string(ctx, -1, OC_OBJECT_MARK)){
        void*  ref = duk_to_pointer(ctx, -1);
        if(ref != NULL){
            NSLog(@"ScriptEngine oc_object_finalizer empty_oc_object_finalizer for alloc");
        }
    }
    return 0;
}

static int oc_object_finalizer(duk_context *ctx) {
    if(duk_get_prop_string(ctx, -1, OC_OBJECT_MARK)){
        void*  ref = duk_to_pointer(ctx, -1);
        if(ref != NULL){
            NSLog(@"ScriptEngine oc_object_finalizer find free new_oc_object %ld %@",   CFGetRetainCount(ref),  ref);
            CFRelease(ref);
        }
    }else{
        NSLog(@"ScriptEngine oc_object_finalizer error, none find free oc_object");
    }
    return 0;

}

static int duk_new_oc_class(duk_context *ctx){
    
    int n = duk_get_top(ctx);
    duk_push_this(ctx);
    duk_get_prop_string(ctx, -1, PROTOTYPE_CLASS_NAME_KEY);
    const char*  className = duk_to_string(ctx, -1);
    Class classRef = objc_getClass(className);
    
    id instance = nil;
    if(n >= 1){
        //FIXME 对于UIView类以及其子类，采用更通用的方法初始化，允许传入frame参数。方便快速初始化
        instance = [[classRef alloc] init];
    }else{
        instance = [[classRef alloc] init];
    }
    NSLog(@"ScriptEngine duk_new_oc_class %s success", className);
    duk_mark_js_to_oc_object(ctx, -2,(__bridge_retained void *)(instance));
    duk_pop_2(ctx);
    return 0;
}



static void duk_mark_js_to_oc_object(duk_context *ctx, int index, void * ref){
    duk_push_pointer(ctx, ref);
    NSLog(@"ScriptEngine duk_push_pointer success %d", index);
    duk_put_prop_string(ctx, index - 1, OC_OBJECT_MARK);
    duk_push_c_function(ctx,  oc_object_to_string, 0);
    duk_put_prop_string(ctx, index - 1, "toString");
    duk_push_c_function(ctx, oc_object_finalizer, 1);
    duk_set_finalizer(ctx, index - 1);
    NSLog(@"ScriptEngine new_oc_object success");
}




static int duk_import_oc_class(duk_context *ctx){
    int n = duk_get_top(ctx);
    for(int i=0; i<n; i++){
        const char* className = duk_to_string(ctx, i);
        NSLog(@"DuktapeEngine import object-c className %s ",className);

        Class classRef = objc_getClass(className);
        if(classRef == nil){
            printf("cann't find class %s", className);
            return 0;
        }

        duk_push_global_object(ctx);
        duk_push_c_function(ctx, duk_new_oc_class,  DUK_VARARGS);
        duk_mark_js_to_oc_object(ctx, -1,  (__bridge_retained void *)(classRef));
        duk_push_object(ctx);
        duk_push_string(ctx, className);
        duk_put_prop_string(ctx, -2,  PROTOTYPE_CLASS_NAME_KEY);
        duk_put_prop_string(ctx, -2,  PROTOTYPE_KEY);
        duk_put_prop_string(ctx, -2,  className);
        duk_pop(ctx);
    }
    return 0;
}

void duk_push_oc_object(duk_context *ctx, id ref){
    if(ref == NULL){
        duk_push_null(ctx);
        return;
    }
    if([ref isKindOfClass:[JSRef class]]){
        int  jsRef =  [ref jsRef];
        duk_push_js_ref(ctx, jsRef);
        return;
    }
    void* object = (__bridge_retained  void *)ref;

    
    //FIXME 引用重用？简单优化？ 和  this对比，
    // 是否要维护关系呢？
    
    /**
     id ref = (__bridge id)(object);
     if([ref isKindOfClass:[JSRef class]]){
     int  ref =  ref.jsRef;
     duk_push_js_ref(ctx, ref);
     return;
     }
    if((*env)->IsInstanceOf(env, object, js_ref_class)){
        jint  ref = (*env)->CallIntMethod(env, object, js_ref_get_ref_method);
        duk_push_js_ref(ctx, ref);
        return;
    }
    if((*env)->IsInstanceOf(env, object, java_number_class)){
        jdouble num = (*env)->CallDoubleMethod(env, object, java_number_get_double_value_method);
        duk_push_global_object(ctx);
        duk_get_prop_string(ctx, -1, "Number");
        duk_push_number(ctx, num);
        duk_new(ctx, 1);
        duk_mark_jsobject_to_java_object(ctx, -1, env, object);
        duk_remove(ctx, -2);
        return;
    }
    if((*env)->IsInstanceOf(env, object, java_boolean_class)){
        jboolean value = (*env)->CallBooleanMethod(env, object, java_boolean_get_boolean_value_method);
        if(value){
            duk_push_true(ctx);
        }else{
            duk_push_false(ctx);
        }
        return;
    }*/
    duk_push_object(ctx);  //empty target
    duk_mark_js_to_oc_object(ctx, -1, object);
}


/**
 *  https://developer.apple.com/library/ios/documentation/Cocoa/Conceptual/ObjCRuntimeGuide/Articles/ocrtTypeEncodings.html#//apple_ref/doc/uid/TP40008048-CH100-SW1
 *  返回NULL 代表不释放内存引用，仅仅通过引用传递
 */
void* duk_to_oc_object(duk_context *ctx, int i,NSInvocation*  invocation){
    const char* type  = [invocation.methodSignature getArgumentTypeAtIndex:i + 1];
    void *value = NULL;
    switch (type[0]) {
        case _C_ID:
        case _C_CLASS:{
                if(duk_get_prop_string(ctx, i, OC_OBJECT_MARK)){
                    void* args = (duk_to_pointer(ctx, -1));
                    NSLog(@"ScriptEngine invoke_oc_method call, convert %d args to oc %@", i, args);
                    [invocation setArgument:&args atIndex:i + 1];
                    duk_pop(ctx);
                    return  NULL;
                }else{
                    duk_pop(ctx);
                    if(duk_get_prop_string(ctx, i, JS_REF_MARK)){
                        NSLog(@"ScriptEngine reuse javascript object's JSRef");
                        void* args  = duk_to_pointer(ctx, -1);
                        [invocation setArgument:&args atIndex:i + 1];
                        duk_pop(ctx);
                        return NULL;
                    }else{
                        duk_pop(ctx);
                    }

                    int type = duk_get_type(ctx, i);
                    if(type == DUK_TYPE_BOOLEAN){
                         NSNumber* number = [NSNumber numberWithBool:duk_to_boolean(ctx, i)];
                        value = (__bridge_retained  void *)(number);
                        [invocation setArgument:&number atIndex:i + 1];
                        return value;
                    }else if (type == DUK_TYPE_NUMBER){
                        NSNumber* number = [NSNumber numberWithDouble:duk_to_number(ctx, i)];
                        value = (__bridge_retained  void *)(number);
                        [invocation setArgument:&number atIndex:i + 1];
                        return  value;
                    }else if(type == DUK_TYPE_STRING){
                        const char*  chs = duk_to_string(ctx, i);
                         NSString* src = [NSString stringWithUTF8String:chs];
                         value = (__bridge_retained  void *)(src);
                        [invocation setArgument:&src atIndex:i + 1];
                        return value;
                    }else if(type == DUK_TYPE_OBJECT){
                        duk_dup(ctx, i);
                        int ref = duk_js_ref(ctx);
                        if(ref != 0){
                            NSLog(@"ScriptEngine convert javascript object to JSRef Ref Value %d ", ref);
                            JSRef* jsRef = [[JSRef alloc] initWithRef:ref engine:get_engine_from_context(ctx)];
                            void* pointer = (__bridge_retained  void *)(jsRef);
                            duk_dup(ctx, i);
                            duk_push_pointer(ctx, pointer);
                            duk_put_prop_string(ctx, -2, JS_REF_MARK);
                            duk_pop(ctx);
                            [invocation setArgument:&pointer atIndex:i + 1];
                            NSLog(@"ScriptEngine convert javascript object to JSRef Ref Value Success");
                            return NULL;
                        }
                    }
                    return NULL;
                }
            }
            return value;
            break;
        case _C_SEL:{
                    void* pointer =  duk_to_pointer(ctx, i);
                    if(pointer){
                        size_t size = sizeof(void*);
                        value = calloc(1, size);
                        memcpy(value, pointer, size);
                        [invocation setArgument:value atIndex:i+1];
                        return value;
                    }
             }
            break;
        case _C_CHR:{
                char  valueInt = duk_to_int(ctx, i);
                value = calloc(1, sizeof(int));
                *((char*)value) = valueInt;
                 [invocation setArgument:value atIndex:i+1];
                 return value;
              }
             break;
        case _C_UCHR:{
                unsigned char  valueInt = duk_to_int(ctx, i);
                value = calloc(1, sizeof(unsigned char));
                *((unsigned char*)value) = valueInt;
                [invocation setArgument:value atIndex:i+1];
                return value;
            }
            break;
        case _C_SHT:{
                short  valueInt = duk_to_int(ctx, i);
                value = calloc(1, sizeof(short));
                *((short*)value) = valueInt;
                [invocation setArgument:value atIndex:i+1];
                return value;
            }
            break;
        case _C_USHT:{
                unsigned short  valueInt = duk_to_int(ctx, i);
                value = calloc(1, sizeof(unsigned short));
                *((unsigned short*)value) = valueInt;
                [invocation setArgument:value atIndex:i+1];
                return value;
            }
            break;
        case _C_INT:{
                int valueInt = duk_to_int(ctx, i);
                value = calloc(1, sizeof(int));
                *((int*)value) = valueInt;
                [invocation setArgument:value atIndex:i+1];
                return value;
            }
            break;
        case _C_UINT:{
                unsigned int valueInt = duk_to_int(ctx, i);
                value = calloc(1, sizeof(unsigned int));
                *((unsigned int*)value) = valueInt;
                [invocation setArgument:value atIndex:i+1];
                return value;
            }
            break;
        case _C_LNG:{
                long valueInt = duk_to_int(ctx, i);
                value = calloc(1, sizeof(long));
                *((long*)value) = valueInt;
                [invocation setArgument:value atIndex:i+1];
                return value;
            }
            break;
        case _C_ULNG:{
                unsigned long valueInt = duk_to_int(ctx, i);
                value = calloc(1, sizeof(unsigned long));
                *((unsigned long*)value) = valueInt;
                [invocation setArgument:value atIndex:i+1];
                return value;
            }
            break;
        case _C_LNG_LNG:{
                 long long valueInt = duk_to_int(ctx, i);
                value = calloc(1, sizeof(long long));
                *((long long*)value) = valueInt;
                [invocation setArgument:value atIndex:i+1];
                return value;
            }
            break;
        case _C_ULNG_LNG:{
                unsigned long long valueInt = duk_to_int(ctx, i);
                value = calloc(1, sizeof(unsigned long long));
                *((unsigned long long*)value) = valueInt;
                [invocation setArgument:value atIndex:i+1];
                return value;
            }
            break;
        case _C_FLT:{
                float valueInt = duk_to_number(ctx, i);
                value = calloc(1, sizeof(float));
                *((float*)value) = valueInt;
                [invocation setArgument:value atIndex:i+1];
                return value;
            }
            break;
        case _C_DBL:{
                double valueInt = duk_to_number(ctx, i);
                value = calloc(1, sizeof(double));
                *((double*)value) = valueInt;
                [invocation setArgument:value atIndex:i+1];
                return value;
            }
            break;
        case _C_BFLD:{
                BOOL valueInt = duk_to_boolean(ctx, i);
                value = calloc(1, sizeof(BOOL));
                *((BOOL*)value) = valueInt;
                [invocation setArgument:value atIndex:i+1];
                return value;
            }
            break;
        case _C_BOOL:{
                BOOL valueInt = duk_to_boolean(ctx, i);
                value = calloc(1, sizeof(BOOL));
                *((BOOL*)value) = valueInt;
                [invocation setArgument:value atIndex:i+1];
                return value;
            }
            break;
        case _C_VOID:
            value = NULL;
            break;
        case _C_UNDEF:
            NSLog(@"error function pointer not supported");
            break;
        case _C_PTR:
        case _C_CHARPTR:{
               void* src =  duk_to_pointer(ctx, i);
               [invocation setArgument:src atIndex:i+1];
               return NULL;
            }
            break;
        case _C_ATOM:
            NSLog(@"error _C_ATOM function pointer not supported");
            break;
        case _C_ARY_B:{
                //FIXME array
                void* src =  duk_to_pointer(ctx, i);
                [invocation setArgument:src atIndex:i+1];
                return NULL;
            }
            break;
        case _C_ARY_E:
            value = duk_to_pointer(ctx, i);
            break;
        case _C_UNION_B:
            value = duk_to_pointer(ctx, i);
            break;
        case _C_UNION_E:
            value = duk_to_pointer(ctx, i);
            break;
        case _C_STRUCT_B:{
               type++;
               if(strncmp(type, "CGRect", 6) == 0){
                   duk_push_string(ctx, "x");
                   duk_get_prop(ctx, i);
                   duk_push_string(ctx, "y");
                   duk_get_prop(ctx, i);
                   duk_push_string(ctx, "width");
                   duk_get_prop(ctx, i);
                   duk_push_string(ctx, "height");
                   duk_get_prop(ctx, i);
                   CGRect rect  = CGRectMake(duk_to_number(ctx, -4), duk_to_number(ctx, -3),duk_to_number(ctx, -2), duk_to_number(ctx, -1));
                   value = calloc(1, sizeof(CGRect));
                   memcpy(value, &rect, sizeof(CGRect));
                   [invocation setArgument:value  atIndex:i+ 1];
                   duk_pop_n(ctx, 4);
               }else if(strncmp(type, "CGPoint", 7) == 0){
                   duk_push_string(ctx, "x");
                   duk_get_prop(ctx, i);
                   duk_push_string(ctx, "y");
                   duk_get_prop(ctx, i);
                   CGPoint point = CGPointMake(duk_to_number(ctx, -2), duk_to_number(ctx, -1));
                   value = malloc(sizeof(CGPoint));
                   memcpy(value, &point, sizeof(CGPoint));
                   [invocation setArgument:value atIndex:i + 1];
                   duk_pop_2(ctx);
               }else if(strncmp(type, "CGSize", 6) == 0){
                   duk_push_string(ctx, "width");
                   duk_get_prop(ctx, i);
                   duk_push_string(ctx, "height");
                   duk_get_prop(ctx, i);
                   CGSize size = CGSizeMake(duk_to_number(ctx, -2), duk_to_number(ctx, -1));
                   value = malloc(sizeof(CGSize));
                   memcpy(value, &size, sizeof(CGSize));
                   [invocation setArgument:value atIndex:i + 1];
                   duk_pop_2(ctx);
               }else if(strncmp(type, "NSRange", 7) == 0){
                   duk_push_string(ctx, "location");
                   duk_get_prop(ctx, i);
                   duk_push_string(ctx, "length");
                   duk_get_prop(ctx, i);
                   NSRange range = NSMakeRange(duk_to_number(ctx, -2), duk_to_number(ctx, -1));
                   value = malloc(sizeof(NSRange));
                   memcpy(value, &range, sizeof(NSRange));
                  [invocation setArgument:value atIndex:i + 1];
                   duk_pop_2(ctx);
               }else if(strncmp(type, "CGVector", 8) == 0){
                   duk_push_string(ctx, "dx");
                   duk_get_prop(ctx, i);
                   duk_push_string(ctx, "dy");
                   duk_get_prop(ctx, i);
                   CGVector vector = CGVectorMake(duk_to_number(ctx, -2), duk_to_number(ctx, -1));
                   value = malloc(sizeof(CGVector));
                   memcpy(value, &vector, sizeof(CGVector));
                   [invocation setArgument:value atIndex:i + 1];
                   duk_pop_2(ctx);
               }else if(strncmp(type, "UIEdgeInsets", 12) == 0){
                   duk_push_string(ctx, "top");
                   duk_get_prop(ctx, i);
                   duk_push_string(ctx, "left");
                   duk_get_prop(ctx, i);
                   duk_push_string(ctx, "bottom");
                   duk_get_prop(ctx, i);
                   duk_push_string(ctx, "right");
                   duk_get_prop(ctx, i);
                   UIEdgeInsets rect  = UIEdgeInsetsMake(duk_to_number(ctx, -4), duk_to_number(ctx, -3),duk_to_number(ctx, -2), duk_to_number(ctx, -1));
                   value = calloc(1, sizeof(UIEdgeInsets));
                   memcpy(value, &rect, sizeof(UIEdgeInsets));
                   [invocation setArgument:value  atIndex:i+ 1];
                   duk_pop_n(ctx, 4);
               }else if(strncmp(type, "UIOffset", 8) == 0){
                   duk_push_string(ctx, "horizontal");
                   duk_get_prop(ctx, i);
                   duk_push_string(ctx, "vertical");
                   duk_get_prop(ctx, i);
                   UIOffset offset = UIOffsetMake(duk_to_number(ctx, -2), duk_to_number(ctx, -1));
                   value = malloc(sizeof(UIOffset));
                   memcpy(value, &offset, sizeof(UIOffset));
                   [invocation setArgument:value atIndex:i + 1];
                   duk_pop_2(ctx);
               }else{
                   NSLog(@"struct %s not implement", type);
                   value = NULL;
               }
            }
            break;
        case _C_STRUCT_E:
             NSLog(@"error _C_STRUCT_E function pointer not supported");
             break;
        case _C_VECTOR:
            NSLog(@"error _C_VECTOR function pointer not supported");
            break;
        case _C_CONST:
            NSLog(@"error _C_CONST function pointer not supported");
            break;
        default:
            break;
    }
    return value;
}

static void duk_push_return_value(duk_context *ctx, NSInvocation*  invocation){
    const char* type = [invocation.methodSignature methodReturnType];
    switch (type[0]) {
        case _C_ID:{
                if(type[1] == '?'){// function pointer, 
                    id result;
                    [invocation getReturnValue:&result];
                    id  returnValue = result;
                    duk_push_oc_object(ctx, returnValue);
                }else{
                    id result;
                    [invocation getReturnValue:&result];
                    id  returnValue = result;
                    duk_push_oc_object(ctx, returnValue);
                }
            }
            break;
        case _C_CLASS:{
                  Class result;
                  [invocation getReturnValue:&result];
                  id  returnValue = result;
                  duk_push_oc_object(ctx, returnValue);
             }
            break;
        case _C_SEL:{
                SEL result;
                [invocation getReturnValue:&result];
                size_t size = sizeof(SEL);
                void* value = duk_alloc(ctx, size);
                memcpy(value, result, size);
                duk_push_pointer(ctx, value);
            }
            break;
        case _C_CHR:{
               char result;
               [invocation getReturnValue:&result];
               duk_push_int(ctx, result);
            }
            break;
        case _C_UCHR:{
                 unsigned char result;
                 [invocation getReturnValue:&result];
                 duk_push_int(ctx, result);
            }
            break;
        case _C_SHT:{
                short result;
                [invocation getReturnValue:&result];
                duk_push_int(ctx, result);
            }
            break;
        case _C_USHT:{
                unsigned short result;
                [invocation getReturnValue:&result];
                duk_push_int(ctx, result);
            }
            break;
        case _C_INT:{
                int  result;
                [invocation getReturnValue:&result];
                duk_push_int(ctx, result);
            }
            break;
        case _C_UINT:{
                unsigned int  result;
                [invocation getReturnValue:&result];
                duk_push_int(ctx, result);
            }
            break;
        case _C_LNG:{
                long  result;
                [invocation getReturnValue:&result];
                duk_push_int(ctx, (int)result);
            }
            break;
        case _C_ULNG:{
                unsigned long  result;
                [invocation getReturnValue:&result];
                duk_push_int(ctx, (int)result);
            }
            break;
        case _C_LNG_LNG:{
                long long  result;
                [invocation getReturnValue:&result];
                duk_push_int(ctx, (int)result);
            }
            break;
        case _C_ULNG_LNG:{
                unsigned long long  result;
                [invocation getReturnValue:&result];
                duk_push_int(ctx, (int)result);
            }
            break;
        case _C_FLT:{
                 float  result;
                 [invocation getReturnValue:&result];
                 duk_push_number(ctx, result);
            }
            break;
        case _C_DBL:{
                double result;
                [invocation getReturnValue:&result];
                duk_push_number(ctx, result);
            }
            break;
        case _C_BFLD:{
                BOOL result;
                [invocation getReturnValue:&result];
                duk_push_number(ctx, result);
            }
            break;
        case _C_BOOL:{
                BOOL result;
                [invocation getReturnValue:&result];
                duk_push_number(ctx, result);
            }
            break;
        case _C_VOID:{
                 duk_push_null(ctx);
            }
            break;
        case _C_UNDEF:
            duk_push_null(ctx);
            break;
        case _C_PTR:{
                void* ptr;
                 [invocation getReturnValue:&ptr];
                 duk_push_pointer(ctx, ptr);
            }
            break;
        case _C_CHARPTR:{
                char* ptr;
                [invocation getReturnValue:&ptr];
                duk_push_pointer(ctx, ptr);
            }
            break;
        case _C_ATOM:
            duk_push_null(ctx);
            break;
        case _C_ARY_B:{
                void* ptr;
                [invocation getReturnValue:&ptr];
                duk_push_pointer(ctx, ptr);
            }
            break;
        case _C_ARY_E:
            duk_push_null(ctx);
            break;
        case _C_UNION_B:
            duk_push_null(ctx);
            break;
        case _C_UNION_E:
            duk_push_null(ctx);
            break;
        case _C_STRUCT_B:{
                type++;
                if(strncmp(type, "CGRect", 6) == 0){
                    CGRect result;
                    [invocation getReturnValue:&result];
                    duk_push_object(ctx);
                    duk_push_number(ctx, result.origin.x);
                    duk_put_prop_string(ctx, -2, "x");
                    duk_push_number(ctx, result.origin.y);
                    duk_put_prop_string(ctx, -2, "y");
                    duk_push_number(ctx, result.size.width);
                    duk_put_prop_string(ctx, -2, "width");
                    duk_push_number(ctx, result.size.height);
                    duk_put_prop_string(ctx, -2, "height");
                }else if(strncmp(type, "CGPoint", 7) == 0){
                    CGPoint result;
                    [invocation getReturnValue:&result];
                    duk_push_object(ctx);
                    duk_push_number(ctx, result.x);
                    duk_put_prop_string(ctx, -2, "x");
                    duk_push_number(ctx, result.y);
                    duk_put_prop_string(ctx, -2, "y");
                }else if(strncmp(type, "CGSize", 6) == 0){
                    CGSize result;
                    [invocation getReturnValue:&result];
                    duk_push_object(ctx);
                    duk_push_number(ctx, result.width);
                    duk_put_prop_string(ctx, -2, "width");
                    duk_push_number(ctx, result.height);
                    duk_put_prop_string(ctx, -2, "height");
                }else if(strncmp(type, "NSRange", 7) == 0){
                    NSRange result;
                    [invocation getReturnValue:&result];
                    duk_push_object(ctx);
                    duk_push_number(ctx, result.location);
                    duk_put_prop_string(ctx, -2, "location");
                    duk_push_number(ctx, result.length);
                    duk_put_prop_string(ctx, -2, "length");
                }else if(strncmp(type, "CGVector", 8) == 0){
                    CGVector result;
                    [invocation getReturnValue:&result];
                    duk_push_object(ctx);
                    duk_push_number(ctx, result.dx);
                    duk_put_prop_string(ctx, -2, "dx");
                    duk_push_number(ctx, result.dy);
                    duk_put_prop_string(ctx, -2, "dy");
                }else if(strncmp(type, "UIEdgeInsets", 12) == 0){
                    UIEdgeInsets result;
                    [invocation getReturnValue:&result];
                    duk_push_object(ctx);
                    duk_push_number(ctx, result.top);
                    duk_put_prop_string(ctx, -2, "top");
                    duk_push_number(ctx, result.left);
                    duk_put_prop_string(ctx, -2, "left");
                    duk_push_number(ctx, result.bottom);
                    duk_put_prop_string(ctx, -2, "bottom");
                    duk_push_number(ctx, result.right);
                    duk_put_prop_string(ctx, -2, "right");
                }else if(strncmp(type, "UIOffset", 8) == 0){
                    UIOffset result;
                    [invocation getReturnValue:&result];
                    duk_push_object(ctx);
                    duk_push_number(ctx, result.horizontal);
                    duk_put_prop_string(ctx, -2, "horizontal");
                    duk_push_number(ctx, result.vertical);
                    duk_put_prop_string(ctx, -2, "vertical");
                }else{
                    NSLog(@"struct %s not implement", type);
                }
            }
            break;
        case _C_STRUCT_E:
            duk_push_null(ctx);
            break;
        case _C_VECTOR:
            duk_push_null(ctx);
            break;
        case _C_CONST:
            duk_push_null(ctx);
            break;
        default:
            break;
    }
}


/**
 *  https://developer.apple.com/library/ios/documentation/Cocoa/Conceptual/ObjCRuntimeGuide/Articles/ocrtTypeEncodings.html#//apple_ref/doc/uid/TP40008048-CH100-SW1
 */
static int invoke_oc_method_call(duk_context *ctx) {
    int num = duk_get_top(ctx);
    const char * methodName = duk_to_string(ctx, 0);
    NSLog(@"ScriptEngine invoke_oc_method call, method name %s  args num %d",  methodName, (num - 1));
    duk_push_this(ctx);
    if(duk_get_prop_string(ctx, -1, OC_OBJECT_MARK)){
        id  ref = (__bridge  id)(duk_to_pointer(ctx, -1));
        SEL  sel = sel_getUid(methodName);
        Method method =  class_getInstanceMethod([ref class], sel);
        BOOL allocMethod;
        if(method == NULL){
            method = class_getClassMethod([ref class], sel);
            if(strncmp(methodName, "alloc", 5) == 0){
                allocMethod = YES;
            }
        }
       if(method == NULL){
            //FIXME Duktape Throw ?
            NSLog(@"method %s not found on object %@", methodName, ref);
            return 0;
        }
        NSMethodSignature *methodSignature = [NSMethodSignature signatureWithObjCTypes:method_getTypeEncoding(method)];
        NSInvocation*  invocation =  [NSInvocation invocationWithMethodSignature:methodSignature];
        [invocation setSelector:sel];


       NSUInteger  argsNum =  [invocation.methodSignature numberOfArguments];
                   argsNum -= 2; // self _cmd



        void** args = NULL;
        if(argsNum > 0){
             args = calloc(argsNum, sizeof(void*));
        }
        switch (argsNum) {
            case 0:
                 break;
            case 1:{
                    args[0] = duk_to_oc_object(ctx, 1, invocation);
                 }
                break;
            case 2:{
                    args[0] = duk_to_oc_object(ctx, 1, invocation);
                    args[1] = duk_to_oc_object(ctx, 2, invocation);
                }
                break;
            case 3:{
                   args[0] = duk_to_oc_object(ctx, 1, invocation);
                   args[1] = duk_to_oc_object(ctx, 2, invocation);
                   args[2] = duk_to_oc_object(ctx, 3, invocation);
                }
                break;
            case 4:{
                    args[0] = duk_to_oc_object(ctx, 1, invocation);
                    args[1] = duk_to_oc_object(ctx, 2, invocation);
                    args[2] = duk_to_oc_object(ctx, 3, invocation);
                    args[3] = duk_to_oc_object(ctx, 4, invocation);
            }
                break;
            case 5:{
                     args[0] = duk_to_oc_object(ctx, 1, invocation);
                     args[1] = duk_to_oc_object(ctx, 2, invocation);
                     args[2] = duk_to_oc_object(ctx, 3, invocation);
                     args[3] = duk_to_oc_object(ctx, 4, invocation);
                     args[4] = duk_to_oc_object(ctx, 5, invocation);
                }
                break;
            case 6:{
                    args[0] = duk_to_oc_object(ctx, 1, invocation);
                    args[1] = duk_to_oc_object(ctx, 2, invocation);
                    args[2] = duk_to_oc_object(ctx, 3, invocation);
                    args[3] = duk_to_oc_object(ctx, 4, invocation);
                    args[4] = duk_to_oc_object(ctx, 5, invocation);
                    args[5] = duk_to_oc_object(ctx, 6, invocation);
            }
                break;
            case 7:{
                  args[0] = duk_to_oc_object(ctx, 1, invocation);
                  args[1] = duk_to_oc_object(ctx, 2, invocation);
                  args[2] = duk_to_oc_object(ctx, 3, invocation);
                  args[3] = duk_to_oc_object(ctx, 4, invocation);
                  args[4] = duk_to_oc_object(ctx, 5, invocation);
                  args[5] = duk_to_oc_object(ctx, 6, invocation);
                  args[6] = duk_to_oc_object(ctx, 7, invocation);
                }
                break;
            default:
                NSLog(@"too many arguments; method invoke ");
                duk_push_null(ctx);
                return 1;
                break;
        }
        [invocation setTarget:ref];
        [invocation invoke];
         duk_push_return_value(ctx, invocation);

        if(allocMethod){
            duk_push_c_function(ctx, empty_oc_object_finalizer, 1);
            duk_set_finalizer(ctx, -2);
        }


        if(args){
            for(int i=0; i<argsNum; i++){
                void* argsRef = args[i];
                if(argsRef){
                    const char* type  = [invocation.methodSignature getArgumentTypeAtIndex:i + 2];
                    if(type[0] == _C_ID  || type[0] == _C_CLASS){
                        CFRelease(argsRef);
                    }else{
                        free(argsRef);
                    }
                }
            }
            free(args);
        }
        NSLog(@"ScriptEngine invoke_oc_method call args num %p %lu ype", method,  (unsigned long)argsNum);
        return 1;
    }else{
        duk_pop(ctx);
        duk_insert(ctx, 0);
         NSLog(@"ScriptEngine invoke_script_prop call, with args  num %d ", duk_get_top(ctx));
        if(duk_pcall_prop(ctx, 0, num - 1) != DUK_EXEC_SUCCESS){
            NSLog(@"ScriptEngine call %s method %s error %s", duk_to_string(ctx, 0), methodName, duk_js_error_to_string(ctx, -1));
            duk_pop(ctx);
            duk_push_null(ctx);
        }
        NSLog(@"ScriptEngine invoke_script_prop call, duk_get_prop_string with args  num %d ", duk_get_top(ctx));
        return 1;
    }
    return 0;
}

//id duk_to_java_object


-(id)initEngine{
    self = [super init];
    if(self){

        __weak JSEngine* weakSelf = self;
        duk_context *ctx  = duk_create_heap(NULL, NULL, NULL, NULL, &duk_js_fatal_function);
        if(ctx){
            duk_js_ref_setup(ctx);

            duk_push_global_object(ctx);
            duk_push_c_function(ctx, duk_js_log_print, DUK_VARARGS);
            duk_put_prop_string(ctx, -2, "print");



             duk_push_c_function(ctx, duk_import_oc_class, DUK_VARARGS);
             duk_put_prop_string(ctx, -2, "importClass");




             void* enginePtr = (__bridge  void *)(weakSelf);
             duk_push_pointer(ctx, enginePtr);
             duk_put_prop_string(ctx, -2, JS_ENGINE_MARK);



             duk_get_prototype(ctx, -1);
             duk_push_c_function(ctx, invoke_oc_method_call, DUK_VARARGS);
             duk_put_prop_string(ctx, -2, OC_METHOD_MARK);



             //duk_push_c_function(ctx, duk_oc_property_get, DUK_VARARGS);
             //duk_put_prop_string(ctx, -2, OC_GET_MARK);

             /**
             duk_push_c_function(ctx, duk_java_property_set, DUK_VARARGS);
             duk_put_prop_string(ctx, -2, JAVA_SET_MARK);
             duk_pop(ctx);
              */


             duk_pop(ctx);

            duk_pop(ctx);

        }
        self.ctx = ctx;
    }
    return self;

    /**
    self[@"__native__c"] = ^id(){
        return [weakSelf handleNativeCall];

    };


   self[@"importClass"] = ^(NSString* className){
        JSContext* context = [JSContext currentContext];
        Class targetClass = NSClassFromString(className);
        context[className] = [weakSelf toJSValue:targetClass context:context];;

        NSLog(@"import classs %@ ",  className);

    };

    [self setExceptionHandler:^(JSContext *context, JSValue *value) {
        NSLog(@"ExceptionHandler  %@ %@", context, value);
    }];
     */
}


- (void)setObject:(id)object forKey:(NSString*)key{
    duk_context *ctx  = self.ctx;
    if(self.ctx){
        duk_push_global_object(ctx);
        duk_push_oc_object(ctx, object);
        duk_put_prop_string(ctx, -2, [key UTF8String]);
        duk_pop(ctx);
    }
}

- (id)objectForKey:(NSString*)key{
    return nil;
}


-(void)execute:(NSString*)script{
    duk_context *ctx  = self.ctx;
    if(self.ctx){
        const char* src = [script UTF8String];
        if(duk_peval_string(self.ctx, src) != DUK_EXEC_SUCCESS){
            NSLog(@"ScriptEngine eval_string error %s\n", duk_js_error_to_string(ctx, -1));
            duk_push_null(ctx);
        }
    }
}

-(id) handleNativeCall{

    /**
    NSArray * args = [JSContext currentArguments];
    if([args count] <= 0){
        NSLog(@"NativeCall  args number wrong, at least two arguments");
        return nil;
    }
    JSValue* value = args[0];
    if([value isNull]){
        NSLog(@"NativeCall  on nill object, selector %@", [args[1] toString]);
        return  nil;
    }
    id  this = [value toObject];  //第一个参数是this，
    NSString* selectorName = [args[1] toString]; //第二个参数是方法名字
    SEL selector = NSSelectorFromString(selectorName);

    NSLog(@"NativeCall %@  %@", this,  selectorName);

    if(![this respondsToSelector:selector]){
        return  nil;
    }


    NSMethodSignature* signature = [this methodSignatureForSelector:selector];

    NSInvocation *invocation = [NSInvocation invocationWithMethodSignature:signature];



    [invocation setSelector:selector];
    [invocation setTarget:this];


    for(int i=2; i<[args count]; i++){
        [invocation setArgument:(__bridge void * _Nonnull)([args objectAtIndex:i]) atIndex:i - 2];
    }

    [invocation invoke];


    const char * returnType = invocation.methodSignature.methodReturnType;


    JSContext *context = [JSContext currentContext];

    NSLog(@"types %s %s %@ %s", @encode(bool), @encode(BOOL),  @(10), @encode(NSArray));


    void* ref;

    ref = (__bridge void *)(context);

    if(0 == strcmp(returnType, @encode(id))){
        void *result;
        [invocation getReturnValue:&result];
        id  returnValue;
        if([selectorName isEqualToString:@"alloc"]){
          returnValue  =  (__bridge_transfer id)result;
        }else{
           returnValue =  (__bridge id)result;
        }

        return [self toJSValue:returnValue  context: context];
    }else if(0 == strcmp(returnType, @encode(void))){
        return nil;
    }else if(0 == strcmp(returnType, @encode(BOOL))){
        BOOL result;
        [invocation getReturnValue:&result];
        return  [JSValue valueWithBool:result inContext:context];
    }else if(0 == strcmp(returnType, @encode(bool))){
        BOOL result;
        [invocation getReturnValue:&result];
        return  [JSValue valueWithBool:result inContext:context];
    }else if(0 == strcmp(returnType, @encode(char))){
        char result;
        [invocation getReturnValue:&result];
        return [JSValue valueWithInt32:result  inContext:context];
    }
    else if(0 == strcmp(returnType, @encode(short))){
        short result;
        [invocation getReturnValue:&result];
        return [JSValue valueWithInt32:result  inContext:context];
    }
    else if(0 == strcmp(returnType, @encode(int))){
        int result;
        [invocation getReturnValue:&result];
        return [JSValue valueWithInt32:result  inContext:context];
    }else if(0 == strcmp(returnType, @encode(long))){
        long result;
        [invocation getReturnValue:&result];
        return [JSValue valueWithInt32:(int32_t)result  inContext:context];
    }
    else if(0 == strcmp(returnType, @encode(long long))){
        long long result;
        [invocation getReturnValue:&result];
        return [JSValue valueWithInt32:(int32_t)result  inContext:context];
    }
    else if(0 == strcmp(returnType, @encode(unsigned char))){
        unsigned char result;
        [invocation getReturnValue:&result];
        return [JSValue valueWithUInt32:result  inContext:context];
    }
    else if(0 == strcmp(returnType, @encode(unsigned short))){
        unsigned short result;
        [invocation getReturnValue:&result];
        return [JSValue valueWithUInt32:result  inContext:context];
    }
    else if(0 == strcmp(returnType, @encode(unsigned int))){
        unsigned int result;
        [invocation getReturnValue:&result];
        return [JSValue valueWithUInt32:result  inContext:context];
    }
    else if(0 == strcmp(returnType, @encode(unsigned long))){
        unsigned long result;
        [invocation getReturnValue:&result];
        return [JSValue valueWithUInt32:(uint32_t)result  inContext:context];
    }else if(0 == strcmp(returnType, @encode(unsigned long long))){
        unsigned long long result;
        [invocation getReturnValue:&result];
        return [JSValue valueWithUInt32:(uint32_t)result  inContext:context];
    }else if(0 == strcmp(returnType, @encode(float))){
        float result;
        [invocation getReturnValue:&result];
        return [JSValue valueWithDouble:(uint32_t)result  inContext:context];
    }else if(0 == strcmp(returnType, @encode(double))){
        double result;
        [invocation getReturnValue:&result];
        return [JSValue valueWithDouble:(uint32_t)result  inContext:context];
    }else if(0 == strcmp(returnType, @encode(char *))){
        char* result;
        [invocation getReturnValue:&result];
        return [JSValue valueWithDouble:(uint32_t)result  inContext:context];
    }else if(0 == strcmp(returnType, @encode(Class))){
        char* result;
        [invocation getReturnValue:&result];
        return [JSValue valueWithDouble:(uint32_t)result  inContext:context];
    }else if(0 == strcmp(returnType, @encode(SEL))){
        char* result;
        [invocation getReturnValue:&result];
        return [JSValue valueWithDouble:(uint32_t)result  inContext:context];
    }
    //FIXME
     */


    return nil;


}


/**

 https://github.com/kstenerud/Objective-Gems/blob/master/Objective-Gems/NSInvocation%2BSimple.m



-(id) toJSValue:(id)value context: (JSContext*)context{

    if(value == nil){
        return nil;
    }
    if([value isKindOfClass:[NSArray class]]
       || [value isKindOfClass:[NSDictionary class]]
       || [value isKindOfClass:[NSDate class]]){

        //return value;
    }




    JSValue* objectRef = [JSValue valueWithJSValueRef:<#(JSValueRef)#> inContext:<#(JSContext *)#> inContext:context];
    [objectRef setValue:OBJECT_MARK_VALUE  forProperty:OBJECT_MARK];
    return  objectRef
    return  nil;
} */

@end
