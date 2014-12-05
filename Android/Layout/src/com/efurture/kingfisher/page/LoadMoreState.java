package com.efurture.kingfisher.page;


public class LoadMoreState {

	public static final int INIT = 0;
	public static final int LOADING = 1;
	public static final int COMPLETE = 2;
	public static final int ERROR = -1;
	public static final int NONE_MORE = 3;
	
	private int currentPage = 1;
	private int state = INIT;
	
	
	public boolean loadMore(){
		if (state == INIT || state == COMPLETE) {
			state = LOADING;
			currentPage++;
			return true;
		}else if (state == ERROR) {
			state = LOADING;
			return true;
		}
		return false;
	}


	public int getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}


	public int getState() {
		return state;
	}


	public void setState(int state) {
		this.state = state;
	}
	
	public void reset(){
		state = INIT;
		currentPage = 1;
	}
}
