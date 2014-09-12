package br.com.petshop.base.vo;

import java.util.Date;

import br.com.petshop.architecture.entity.BaseEntity;

public class DashBoardVO extends BaseEntity implements Comparable<DashBoardVO>{

	private static final long serialVersionUID = 1L;
	
	private Long countID;
	private Date dataVenda;
	private String nomeRede;
	private String nomeBandeira;
	private Number valorBruto;
	private Double valorLiquido;
	
	public DashBoardVO() {
		super();
	}
	
	public DashBoardVO(String nomeRede) {
		super();
		this.nomeRede = nomeRede;
	}

	public void setNomerede(String nomeRede){
		setNomeRede(nomeRede);
	}
	
	public void setCountid(Number countID){
		setCountID(countID.longValue());
	}
	
	public void setDatavenda(Date dataVenda){
		setDataVenda(dataVenda);
	}
	
	public void setValorbruto(Number valorBruto){
		setValorBruto(valorBruto);
	}

	public Long getCountID() {
		return countID;
	}

	public void setCountID(Long countID) {
		this.countID = countID;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	public String getNomeRede() {
		return nomeRede;
	}

	public void setNomeRede(String nomeRede) {
		this.nomeRede = nomeRede;
	}

	public String getNomeBandeira() {
		return nomeBandeira;
	}

	public void setNomeBandeira(String nomeBandeira) {
		this.nomeBandeira = nomeBandeira;
	}

	public Number getValorBruto() {
		return valorBruto;
	}

	public void setValorBruto(Number valorBruto) {
		this.valorBruto = valorBruto;
	}

	public Double getValorLiquido() {
		return valorLiquido;
	}

	public void setValorLiquido(Double valorLiquido) {
		this.valorLiquido = valorLiquido;
	}

	@Override
	public int compareTo(DashBoardVO o) {
		return this.nomeRede.compareToIgnoreCase(o.nomeRede);
	}
}