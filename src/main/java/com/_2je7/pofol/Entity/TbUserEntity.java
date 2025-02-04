package com._2je7.pofol.Entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tb_user")
@Getter
@Setter
@DynamicInsert
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TbUserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long user_id;
	private String user_lgn_id;
	private String user_lgn_pswd;
	private String salt;
	private String user_nm;
	private String user_telno;
	private String user_eml;
	private String user_addr;
	private String user_daddr;
	private String user_grd;
	@CreationTimestamp
	private Date user_reg_dt;
	private Integer user_stts;
	@UpdateTimestamp
	private Date user_mdfcn_dt;
	private String ogdp_id;
	private Date user_last_cntn_dt;
	private String user_img;
}
