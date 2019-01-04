package com.web.domain.enums;

public enum SocialType {
	FACEBOOK("facebook"),
	GOOGLE("google"),
	KAKAO("kakao");
	
	private final String ROLE_PREFIX = "ROLE_";
	private String name;
	
	SocialType(String name) {
		this.name = name;
	}
	
	public String getRoleType() { return ROLE_PREFIX + name.toUpperCase(); } // "ROLE_*" 형식으로 소셜미디어의 권한 명을 생성
	
	public String getValue() { return name; }
	
	public boolean isEquals(String authority) {
		return this.name.equals(authority);
	}
}
