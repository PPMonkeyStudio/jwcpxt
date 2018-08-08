package com.pphgzs.domain.DO;

/**
 * 
 * @author JXX
 *
 */
public class jwcpxt_entry_exit {
	private String entry_exit_id;
	private String entry_exit_client_name;
	private String entry_exit_client_sex;
	private String entry_exit_client_phone;
	private String entry_exit_client_data;
	private String entry_exit_client_unit;
	private String entry_exit_client_type;
	private String entry_exit_client_id_type;
	private int entry_exit_client_is_distribute;
	private String entry_exit_gmt_create;
	private String entry_exit_gmt_modify;

	public String getEntry_exit_id() {
		return entry_exit_id;
	}

	public void setEntry_exit_id(String entry_exit_id) {
		this.entry_exit_id = entry_exit_id;
	}

	public String getEntry_exit_client_name() {
		return entry_exit_client_name;
	}

	public void setEntry_exit_client_name(String entry_exit_client_name) {
		this.entry_exit_client_name = entry_exit_client_name;
	}

	public String getEntry_exit_client_sex() {
		return entry_exit_client_sex;
	}

	public void setEntry_exit_client_sex(String entry_exit_client_sex) {
		this.entry_exit_client_sex = entry_exit_client_sex;
	}

	public String getEntry_exit_client_phone() {
		return entry_exit_client_phone;
	}

	public void setEntry_exit_client_phone(String entry_exit_client_phone) {
		this.entry_exit_client_phone = entry_exit_client_phone;
	}

	public String getEntry_exit_client_data() {
		return entry_exit_client_data;
	}

	public void setEntry_exit_client_data(String entry_exit_client_data) {
		this.entry_exit_client_data = entry_exit_client_data;
	}

	public String getEntry_exit_client_unit() {
		return entry_exit_client_unit;
	}

	public void setEntry_exit_client_unit(String entry_exit_client_unit) {
		this.entry_exit_client_unit = entry_exit_client_unit;
	}

	public String getEntry_exit_client_type() {
		return entry_exit_client_type;
	}

	public void setEntry_exit_client_type(String entry_exit_client_type) {
		this.entry_exit_client_type = entry_exit_client_type;
	}

	public String getEntry_exit_client_id_type() {
		return entry_exit_client_id_type;
	}

	public void setEntry_exit_client_id_type(String entry_exit_client_id_type) {
		this.entry_exit_client_id_type = entry_exit_client_id_type;
	}

	public int getEntry_exit_client_is_distribute() {
		return entry_exit_client_is_distribute;
	}

	public void setEntry_exit_client_is_distribute(int entry_exit_client_is_distribute) {
		this.entry_exit_client_is_distribute = entry_exit_client_is_distribute;
	}

	public String getEntry_exit_gmt_create() {
		return entry_exit_gmt_create;
	}

	public void setEntry_exit_gmt_create(String entry_exit_gmt_create) {
		this.entry_exit_gmt_create = entry_exit_gmt_create;
	}

	public String getEntry_exit_gmt_modify() {
		return entry_exit_gmt_modify;
	}

	public void setEntry_exit_gmt_modify(String entry_exit_gmt_modify) {
		this.entry_exit_gmt_modify = entry_exit_gmt_modify;
	}

	@Override
	public String toString() {
		return "jwcpxt_entry_exit [entry_exit_id=" + entry_exit_id + ", entry_exit_client_name="
				+ entry_exit_client_name + ", entry_exit_client_sex=" + entry_exit_client_sex
				+ ", entry_exit_client_phone=" + entry_exit_client_phone + ", entry_exit_client_data="
				+ entry_exit_client_data + ", entry_exit_client_unit=" + entry_exit_client_unit
				+ ", entry_exit_client_type=" + entry_exit_client_type + ", entry_exit_client_id_type="
				+ entry_exit_client_id_type + ", entry_exit_client_is_distribute=" + entry_exit_client_is_distribute
				+ ", entry_exit_gmt_create=" + entry_exit_gmt_create + ", entry_exit_gmt_modify="
				+ entry_exit_gmt_modify + "]";
	}

	public jwcpxt_entry_exit(String entry_exit_id, String entry_exit_client_name, String entry_exit_client_sex,
			String entry_exit_client_phone, String entry_exit_client_data, String entry_exit_client_unit,
			String entry_exit_client_type, String entry_exit_client_id_type, int entry_exit_client_is_distribute,
			String entry_exit_gmt_create, String entry_exit_gmt_modify) {
		super();
		this.entry_exit_id = entry_exit_id;
		this.entry_exit_client_name = entry_exit_client_name;
		this.entry_exit_client_sex = entry_exit_client_sex;
		this.entry_exit_client_phone = entry_exit_client_phone;
		this.entry_exit_client_data = entry_exit_client_data;
		this.entry_exit_client_unit = entry_exit_client_unit;
		this.entry_exit_client_type = entry_exit_client_type;
		this.entry_exit_client_id_type = entry_exit_client_id_type;
		this.entry_exit_client_is_distribute = entry_exit_client_is_distribute;
		this.entry_exit_gmt_create = entry_exit_gmt_create;
		this.entry_exit_gmt_modify = entry_exit_gmt_modify;
	}

	public jwcpxt_entry_exit() {
		super();
	}

}
