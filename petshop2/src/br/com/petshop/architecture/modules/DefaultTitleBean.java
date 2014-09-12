package br.com.petshop.architecture.modules;

import java.util.HashMap;
import java.util.Map;

import br.com.petshop.architecture.util.JSFUtil;

public class DefaultTitleBean {

	protected static final Map<String, String> titles = new HashMap<String, String>();
	
	protected String pageTitle = "noTitleDefined";
	protected static String titleSearch = "lblSearch";
	protected static String titleInsert = "lblInsert";
	protected static String titleEdit = "lbl_editar";
    protected static String titleView = "lblView";
    protected static String titleViewDetail = "lblViewDetail";
    protected static String titleGraphic = "lblGraphic";
    protected static String titleGraphicDetail = "lblGraphicDetail";
    
	static {
		DefaultTitleBean.titles.put(null, titleSearch);
		DefaultTitleBean.titles.put(DefaultStateBean.STATE_SEARCH, titleSearch);
		DefaultTitleBean.titles.put(DefaultStateBean.STATE_INSERT, titleInsert);
		DefaultTitleBean.titles.put(DefaultStateBean.STATE_EDIT, titleEdit);
		DefaultTitleBean.titles.put(DefaultStateBean.STATE_VIEW, titleView);
		DefaultTitleBean.titles.put(DefaultStateBean.STATE_VIEW_DETAIL, titleViewDetail);
		DefaultTitleBean.titles.put(DefaultStateBean.STATE_GRAPHIC_DETAIL, titleGraphicDetail);
		DefaultTitleBean.titles.put(DefaultStateBean.STATE_GRAPHIC, titleGraphic);
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
	
	public String getFullTitle(DefaultStateBean stateBeanManager) {
		
		String title = "";
		if(this.pageTitle == null){
			this.pageTitle = "noTitleDefined";
		}
		
		if(stateBeanManager.getCurrentState() == null){
			stateBeanManager.setCurrentState(DefaultStateBean.STATE_SEARCH);
		}
		
		if ("noTitleDefined".equals(this.pageTitle)) {
			return JSFUtil.getMessageFromBundle("noTitleDefined");
		} else if(stateBeanManager.getCurrentState().equals("viewGraphic")){
			title += JSFUtil.getMessageFromBundle(this.pageTitle);
			title += " - ";
			title += "Gráfico";
		} else {
			title += JSFUtil.getMessageFromBundle(this.pageTitle);
			title += " - ";
			String titleCurrentState = this.getTitleFromThePage(stateBeanManager);
			if(titleCurrentState == null){
				return null;
			}
			title += titleCurrentState;
		}
		return title;
	}

	public String getTitleFromThePage(DefaultStateBean stateBeanManager) {
		String currentState = stateBeanManager.getCurrentState();
		if(currentState == null){
			currentState = pageTitle;
		}
		
		String titleCurrentState = titles.get(currentState);
		if(titleCurrentState == null){
			return null;
		}
		try{
			return JSFUtil.getMessageFromBundle(titleCurrentState);
		}catch(Exception e){
			throw new TitleBeanNotFoundException(" A traducao para [" + titleCurrentState + "] nao foi encontrado no arquivo de propriedades." , e);
		}
	}
}
