package com._2je7.pofol.Entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
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
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "user_lgn_id")
	private String userLgnId;
	
	@Column(name = "user_lgn_pswd")
	private String userLgnPswd;
	
	@Column(name = "salt")
	private String salt;
	
	@Column(name = "user_nm")
	private String userNm;
	
	@Column(name = "user_telno")
	private String userTelno;
	
	@Column(name = "user_eml")
	private String userEml;
	
	@Column(name = "user_addr")
	private String userAddr;
	
	@Column(name = "user_daddr")
	private String userDaddr;
	
	@Column(name = "user_grd")
	private String userGrd;
	
	@CreationTimestamp
	@Column(name = "user_reg_dt")
	private Date userRegDt;
	
	@Column(name = "user_stts")
	private Integer userStts;
	
	@UpdateTimestamp
	@Column(name = "user_mdfcn_dt")
	private Date userMdfcnDt;
	
	@Column(name = "ogdp_id")
	private String ogdpId;
	
	@Column(name = "user_last_cntn_dt")
	private Date userLastCntnDt;
	
	@Column(name = "user_img")
	private String userImg;
}
