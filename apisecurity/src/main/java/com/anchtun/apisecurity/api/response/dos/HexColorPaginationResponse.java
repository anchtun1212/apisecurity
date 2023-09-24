package com.anchtun.apisecurity.api.response.dos;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HexColorPaginationResponse {
	private int currentPage;
	private int size;
	private int totalPages;
	private List<HexColor> colors;
}
