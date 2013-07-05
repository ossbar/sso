<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<div class="datagrid-pager pagination" region="south" border="false" style="background: #EFEFEF;">
	<input type="hidden" name="pages" value="${pages}"/>
	<input type="hidden" name="orders" value="${orders}"/>
	<table cellspacing="0" cellpadding="0" border="0">
		<tbody>
			<tr>
				<td><select class="pagination-page-list" name="rows">
					<c:forTokens items="15,20,30,40,50" delims="," var="rw">
						<option	<c:if test="${rows== rw}">selected</c:if>>${rw}</option>
					</c:forTokens>
				</select>
				</td>
				<td><div class="pagination-btn-separator"></div>
				</td>
				<td><a href="javascript:void(0)" icon="pagination-first"
					class="l-btn l-btn-plain"><span
						class="l-btn-left"><span class="l-btn-text"><span
								class="l-btn-empty pagination-first">&nbsp;</span>
						</span>
					</span>
				</a>
				</td>
				<td><a href="javascript:void(0)" icon="pagination-prev"
					class="l-btn l-btn-plain"><span
						class="l-btn-left"><span class="l-btn-text"><span
								class="l-btn-empty pagination-prev">&nbsp;</span>
						</span>
					</span>
				</a>
				</td>
				<td><div class="pagination-btn-separator"></div>
				</td>
				<td><span style="padding-left: 6px;">当前第</span>
				</td>
				<td><input class="pagination-num" name="page" value="${page}" type="text" size="2">
				</td>
				<td><span style="padding-right: 6px;">页,共 ${pages}页</span>
				</td>
				<td><div class="pagination-btn-separator"></div>
				</td>
				<td><a href="javascript:void(0)" icon="pagination-next"
					class="l-btn l-btn-plain"><span
						class="l-btn-left"><span class="l-btn-text"><span
								class="l-btn-empty pagination-next">&nbsp;</span>
						</span>
					</span>
				</a>
				</td>
				<td><a href="javascript:void(0)" icon="pagination-last"
					class="l-btn l-btn-plain"><span
						class="l-btn-left"><span class="l-btn-text"><span
								class="l-btn-empty pagination-last">&nbsp;</span>
						</span>
					</span>
				</a>
				</td>
				<td><div class="pagination-btn-separator"></div>
				</td>
				<td><a href="javascript:void(0)" icon="pagination-load"
					class="l-btn l-btn-plain"><span class="l-btn-left"><span
							class="l-btn-text"><span
								class="l-btn-empty pagination-load">&nbsp;</span>
						</span>
					</span>
				</a>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="pagination-info">共  ${total} 条记录</div>
	<div style="clear: both;"></div>
</div>
