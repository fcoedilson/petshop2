package br.com.petshop.architecture.modules;

public class DefaultStateBean {
	
	public static final String STATE_SEARCH = "search";
	public static final String STATE_EDIT = "edit";
	public static final String STATE_INSERT = "insert";
	public static final String STATE_DELETE = "delete";
	public static final String STATE_VIEW = "view";
	public static final String STATE_VIEW_DETAIL = "viewDetail";
	public static final String STATE_GRAPHIC = "viewGraphic";
	public static final String STATE_GRAPHIC_DETAIL = "viewGraphicDetail";
	public static final String STATE_UPLOAD= "viewUpload";
	public static final String STATE_COMPROVANTE = "comprovante";

	private String currentState;
	
	public String getCurrentState() {
		return this.currentState;
	}
	
	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}
	
	public boolean isEditing() {
		return STATE_EDIT.equals(getCurrentState());
	}

	public boolean isInserting() {
		return STATE_INSERT.equals(getCurrentState());
	}

	public boolean isDeleting() {
		return STATE_DELETE.equals(getCurrentState());
	}

	public boolean isViewing() {
		return STATE_VIEW.equals(getCurrentState());
	}
	
	public boolean isShowingGraphic() {
		return STATE_GRAPHIC.equals(getCurrentState());
	}

	public boolean isShowingGraphicDetail() {
		return STATE_GRAPHIC_DETAIL.equals(getCurrentState());
	}

	public boolean isUpdating() {
		return (this.isEditing() || this.isInserting() || this.isDeleting());
	}
	
	public boolean isSearching() {
		return (getCurrentState() == null || getCurrentState().equals("") || STATE_SEARCH.equals(getCurrentState()));
	}
}