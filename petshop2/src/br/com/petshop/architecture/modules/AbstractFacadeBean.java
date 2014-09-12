package br.com.petshop.architecture.modules;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.lang.StringUtils;
import org.richfaces.component.UICollapsiblePanel;

import br.com.petshop.architecture.actions.ActionDisable;
import br.com.petshop.architecture.actions.ActionRemove;
import br.com.petshop.architecture.actions.ActionSave;
import br.com.petshop.architecture.actions.ActionSearch;
import br.com.petshop.architecture.entity.IEntity;
import br.com.petshop.architecture.report.ReportService;
import br.com.petshop.architecture.service.Disabilitable;
import br.com.petshop.architecture.util.JSFUtil;
import br.com.petshop.architecture.util.Paginator;
import br.com.petshop.architecture.util.StaticFilter;

public abstract class AbstractFacadeBean<ENTITY extends IEntity> extends StaticFilter implements Serializable {
	
	protected static final long serialVersionUID = 7221343979964412478L;
	public static final String MENSAGEM = "mensagem";
	public static final String SELECIONADOR = "selecionador";
	public static final String LINHAS_PAGINAS = "linhasPaginas";
	public static final String TOTAL_MARCADOS = "totalMarcados";
	public static final String SELECIONOU_TODOS = "selecionouTodos";
	public static final String LINHAS_SELECIONADAS = "linhasSelecionadas";
	protected DefaultEntityBean entityBean = new DefaultEntityBean();
	protected DefaultStateBean stateBean = new DefaultStateBean();
	protected DefaultTitleBean titleBean = new DefaultTitleBean();

	private Boolean search = false;
	private Boolean mostraPainel;
	
	@ManagedProperty(value="#{reportService}")
	private ReportService reportService;
	
	private UICollapsiblePanel uiCollapsiblePanel;

	@PostConstruct
	public void initBean(){}

	protected abstract String getPageTitle();
	
	protected abstract Class<ENTITY> getDefaultEntityClass();
	
	protected ENTITY createDefaultEntity(){
		return entityBean.createDefaultEntity(getDefaultEntityClass());
	}
	
	/**
	 * Chamado ao clicar no botao "NOVO"
	 * @param actionEvent
	 * @return
	 */
	public final void prepareInsert(){
		beforePrepareInsert();
		createDefaultEntity();
		stateBean.setCurrentState(DefaultStateBean.STATE_INSERT);
		afterPrepareInsert();
	}
		
	/**
	 * Chamado ao clicar em editar
	 * @param actionEvent
	 * @return
	 */
	public final void prepareEdit(){
		beforePrepareEdit();
		stateBean.setCurrentState(DefaultStateBean.STATE_EDIT);
		afterPrepareEdit();
	}
	
	@SuppressWarnings("unused")
	public final void prepareEdit(ActionEvent actionEvent){
		beforePrepareEdit();
		stateBean.setCurrentState(DefaultStateBean.STATE_EDIT);
		afterPrepareEdit();
	}
	
	/**
	 * Chamado ao clicar em voltar
	 * @param actionEvent
	 * @return
	 */
	public final void cancel() {
		stateBean.setCurrentState(DefaultStateBean.STATE_SEARCH);
		createDefaultEntity();
		afterCancel();
	}
	
	protected void beforePrepareEdit(){}
	
	protected void beforePrepareInsert(){}
	
	protected void beforeSave(){}
	
	protected void beforeSearch(){}
	
	protected void afterRemove(){}
	
	protected void beforeRemove(){}

	protected void afterSave(){}
	
	protected void afterCancel(){}
	
	protected void afterSearch(){}
	
	@SuppressWarnings("unused")
	protected void afterRemove(ENTITY removedEntity){}
	
	protected void afterPrepareInsert(){}
	
	protected void afterPrepareEdit(){}
	
	public String getTitle(){
		return JSFUtil.getMessageFromBundle(getPageTitle());
	}
	
	
		
	
	public Boolean fromQuadroAviso(){
		if(FacesContext.getCurrentInstance() == null){
			return false;
		}
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String from = params.get("from");
	 	return StringUtils.isNotEmpty(from) && from.equals("quadroAvisos");
	}
	
	
	/**
	 * Chamado para trazer a listagem padrao
	 * @return
	 */
	protected ActionSearch<? extends IEntity> searchAction(){throw new NotImplementedException();}
	
	/**
	 * Usado para salvar a entidade padrao.
	 * @return
	 */
	protected ActionSave<? extends IEntity> saveAction(){throw new NotImplementedException();}
	
	/**
	 * Usado para flegar a entidade padrao como ativa ou desativa. A entidade deve implementar a interface Disabilitable.
	 * @return
	 */
	protected ActionDisable<? extends Disabilitable> disableAction(){throw new NotImplementedException();}
	
	/**
	 * Usado para a entidade padrao da base de dados.
	 * @return
	 */
	protected ActionRemove<? extends IEntity> removeAction(){throw new NotImplementedException();}
	
	protected String gerarRelatorio(){throw new NotImplementedException();}

	//actions
	
	@SuppressWarnings("unchecked")
	public void save() {
		beforeSave();
		if (entityBean.save((ActionSave<IEntity>) saveAction())) {
			afterSave();
			search();
		}
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	public void save(ActionEvent actionEvent) {
		beforeSave();
		if (entityBean.save((ActionSave<IEntity>) saveAction())) {
			afterSave();
			search();
		}
	}

	@SuppressWarnings("unchecked")
	public void save(Boolean changeState) {
		beforeSave();
		if (entityBean.save((ActionSave<IEntity>) saveAction()) && changeState) {
			afterSave();
			search();
		}else{
			createDefaultEntity();
			search(changeState);
		}
	}
	
	public void remove() {
		beforeRemove();
		ENTITY removedEntity = getDefaultEntity();
		if (entityBean.remove(removeAction())) {
			search();
			afterRemove(removedEntity);
		}
	}
	
	@SuppressWarnings("unused")
	public void remove(ActionEvent actionEvent){
		beforeRemove();
		ENTITY removedEntity = getDefaultEntity();
		if (entityBean.remove(removeAction())) {
			search();
			afterRemove(removedEntity);
		}
	}
	
	public void disable() {
		if (entityBean.disable(disableAction())) {
			search();
		}
	}
	
	public void search() {
		beforeSearch();
		commonsSearch();
		if (entityBean.search(searchAction())) {
			stateBean.setCurrentState(DefaultStateBean.STATE_SEARCH);
		}
		afterSearch();
	}
	
	public void search(Boolean changeState) {
		if(changeState && entityBean.getPagedList() != null){
			entityBean.getPagedList().setPaginaClicada(1);
		}
		beforeSearch();
		commonsSearch();
		if (entityBean.search(searchAction()) && changeState) {
			stateBean.setCurrentState(DefaultStateBean.STATE_SEARCH);
		}
		setExpandedCollapsiblePanel();
		afterSearch();
	}
	
	public void search(String newState) {
		commonsSearch();
		if (entityBean.search(searchAction())) {
			stateBean.setCurrentState(newState);
		}
	}
	
	private void commonsSearch() {
		setSearch(true);
		setMostraPainel(true);
	}
		
	private void setExpandedCollapsiblePanel() {
		if(uiCollapsiblePanel == null){
			return;
		}
		uiCollapsiblePanel.setExpanded(getSearchResult() == null || getSearchResult().isEmpty());
	}

	public List<ENTITY> getSearchResult(){
		return entityBean.getSearchResult();
	}

	public void setSearchResult(List<ENTITY> searchResult){
		entityBean.setSearchResult(searchResult);
	}
	
	public Map<String, Object> getBeanProperties() {
		return entityBean.getBeanProperties();
	}
	
	public void clearBeanProperties(){
		entityBean.getBeanProperties().clear();
	}

	public Paginator getPagedList() {
		getBeanProperties().put(SELECIONADOR, false);
		if(entityBean.getPagedList() == null){
			Paginator pagedList = new Paginator() {
				@Override
				public void pesquisar() {
					search(false);
				}
			};
			entityBean.setPagedList(pagedList);
		}
		return entityBean.getPagedList();
	}
	
	public Map<String, Object> getFilter() {
		return entityBean.getFilter();
	}
	
	
	public void setFilter(Map<String, Object> filter) {
		entityBean.setFilter(filter);
	}
	
	public ENTITY getDefaultEntity(){
		return entityBean.getDefaultEntity();
	}

	public void setDefaultEntity(ENTITY defaultEntity){
		entityBean.setDefaultEntity(defaultEntity);
	}
	
	public Map<String, Object> getResultProperties(){
		return entityBean.getResultProperties();
	}

	public String getCurrentState() {
		return stateBean.getCurrentState();
	}

	public 	void setCurrentState(String currentState) {
		stateBean.setCurrentState(currentState);
	}
	
	public boolean isEditing() {
		return stateBean.isEditing();
	}
	
	public boolean isInserting() {
		return stateBean.isInserting();
	}
	
	public boolean isDeleting() {
		return stateBean.isDeleting();
	}
	
	public boolean isViewing() {
		return stateBean.isViewing();
	}
	
	public boolean isUpdating() {
		return stateBean.isUpdating();
	}
	
	public boolean isSearching() {
		return stateBean.isSearching();
	}
	
	public boolean isShowingGraphic() {
		return stateBean.isShowingGraphic();
	}

	public boolean isShowingGraphicDetail() {
		return stateBean.isShowingGraphicDetail();
	}
	
	public String getFullTitle() {
		titleBean.setPageTitle(getPageTitle());
		return titleBean.getFullTitle(stateBean);
	}

	public String getTitleFromThePage() {
		return titleBean.getTitleFromThePage(stateBean);
	}

	public Boolean getSearch() {
		return search;
	}

	public void setSearch(Boolean search) {
		this.search = search;
	}
	
	public Boolean getMostraPainel() {
		return mostraPainel;
	}

	public void setMostraPainel(Boolean mostraPainel) {
		this.mostraPainel = mostraPainel;
	}

	public ReportService getReportService() {
		return reportService;
	}

	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	public UICollapsiblePanel getUiCollapsiblePanel() {
		return uiCollapsiblePanel;
	}

	public void setUiCollapsiblePanel(UICollapsiblePanel uiCollapsiblePanel) {
		this.uiCollapsiblePanel = uiCollapsiblePanel;
	}
	
	//BEAN AUXILIAR
	
	public boolean facesContextIsNull(){
		return FacesContext.getCurrentInstance() == null;
	}
}