package eCore.controller;

public interface ZEController<T> {

	public String addNew();

	public String viewDetail();

	public String viewDetailAndEdit();

	public String saveOrUpdate();

	public String delete();

	public String search();

	public String refresh();

	public String importData();

	public String exportData();

}
