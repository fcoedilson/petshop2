package br.com.petshop.base.factory;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.petshop.base.enumeration.CategoriaAuditoria;
import br.com.petshop.base.enumeration.Layout;
import br.com.petshop.base.enumeration.Status;
import br.com.petshop.base.enumeration.TipoArquivo;
import br.com.petshop.base.enumeration.TipoBoleto;

@ManagedBean
@ViewScoped
public class EnumFactoryBean {
	

	public List<Layout> getListLayout(){
		List<Layout> layouts = new ArrayList<Layout>();
		for(Layout tp : Layout.values()){
			layouts.add(tp);
		}
		return layouts;
	}

	public List<Status> listStatus(){
		List<Status> listStatus = new ArrayList<Status>();
		for (Status status : Status.values()) {
			listStatus.add(status);
		}
		return listStatus;
	}


	public List<TipoBoleto> listTipoBoleto(){
		List<TipoBoleto> listaTipoBoleto = new ArrayList<TipoBoleto>();
		for (TipoBoleto tipo : TipoBoleto.values()) {
			listaTipoBoleto.add(tipo);
		}
		return listaTipoBoleto;
	}
	

	public List<TipoArquivo> listTipoArquivo(){
		List<TipoArquivo> listaTipoArquivo = new ArrayList<TipoArquivo>();
		for (TipoArquivo tipo : TipoArquivo.values()) {
			listaTipoArquivo.add(tipo);
		}
		return listaTipoArquivo;
	}

	public List<CategoriaAuditoria> listCategoriaAuditoria(){
		List<CategoriaAuditoria> listaCategoriaAuditoria = new ArrayList<CategoriaAuditoria>();
		for (CategoriaAuditoria tipo : CategoriaAuditoria.values()) {
			listaCategoriaAuditoria.add(tipo);
		}
		return listaCategoriaAuditoria;
	}
	
}