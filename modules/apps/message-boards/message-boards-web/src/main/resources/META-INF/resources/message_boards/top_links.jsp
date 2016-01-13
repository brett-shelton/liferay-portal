<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/message_boards/init.jsp" %>

<%
String mvcRenderCommandName = ParamUtil.getString(request, "mvcRenderCommandName");
String topLink = ParamUtil.getString(request, "topLink", "message-boards-home");

MBCategory category = (MBCategory)request.getAttribute(WebKeys.MESSAGE_BOARDS_CATEGORY);

long categoryId = MBUtil.getCategoryId(request, category);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcRenderCommandName", "/message_boards/view");
%>

<aui:nav-bar>
	<aui:nav cssClass="navbar-nav">

		<%
		String label = "message-boards-home";

		portletURL.setParameter("topLink", label);
		portletURL.setParameter("tag", StringPool.BLANK);
		%>

		<aui:nav-item cssClass='<%= topLink.equals(label) ? "active" : StringPool.BLANK %>' href="<%= portletURL.toString() %>" label="<%= label %>" selected="<%= topLink.equals(label) %>" />

		<%
		label = "recent-posts";

		portletURL.setParameter("topLink", label);
		%>

		<aui:nav-item cssClass='<%= topLink.equals(label) ? "active" : StringPool.BLANK %>' href="<%= portletURL.toString() %>" label="<%= label %>" selected="<%= topLink.equals(label) %>" />

		<c:if test="<%= themeDisplay.isSignedIn() && !portletName.equals(MBPortletKeys.MESSAGE_BOARDS_ADMIN) %>">

			<%
			label = "my-posts";

			portletURL.setParameter("topLink", label);
			%>

			<aui:nav-item cssClass='<%= topLink.equals(label) ? "active" : StringPool.BLANK %>' href="<%= portletURL.toString() %>" label="<%= label %>" selected="<%= topLink.equals(label) %>" />

			<c:if test="<%= mbGroupServiceSettings.isEmailMessageAddedEnabled() || mbGroupServiceSettings.isEmailMessageUpdatedEnabled() %>">

				<%
				label = "my-subscriptions";

				portletURL.setParameter("topLink", label);
				%>

				<aui:nav-item cssClass='<%= topLink.equals(label) ? "active" : StringPool.BLANK %>' href="<%= portletURL.toString() %>" label="<%= label %>" selected="<%= topLink.equals(label) %>" />
			</c:if>
		</c:if>

		<%
		PortletURL viewStatisticsURL = renderResponse.createRenderURL();

		viewStatisticsURL.setParameter("mvcRenderCommandName", "/message_boards/view_statistics");
		%>

		<aui:nav-item cssClass='<%= mvcRenderCommandName.equals("/message_boards/view_statistics") ? "active" : StringPool.BLANK %>' href="<%= viewStatisticsURL.toString() %>" label="statistics" selected='<%= mvcRenderCommandName.equals("/message_boards/view_statistics") %>' />

		<c:if test="<%= MBPermission.contains(permissionChecker, scopeGroupId, ActionKeys.BAN_USER) %>">

			<%
			PortletURL bannedUsersURL = renderResponse.createRenderURL();

			bannedUsersURL.setParameter("mvcRenderCommandName", "/message_boards/view_banned_users");
			%>

			<aui:nav-item cssClass='<%= mvcRenderCommandName.equals("/message_boards/view_banned_users") ? "active" : StringPool.BLANK %>' href="<%= bannedUsersURL.toString() %>" label="banned-users" selected='<%= mvcRenderCommandName.equals("/message_boards/view_banned_users") %>' />
		</c:if>
	</aui:nav>

	<c:if test="<%= showSearch %>">
		<liferay-portlet:renderURL varImpl="searchURL">
			<portlet:param name="mvcRenderCommandName" value="/message_boards/search" />
		</liferay-portlet:renderURL>

		<aui:nav-bar-search>
			<div class="form-search">
				<aui:form action="<%= searchURL %>" method="get" name="searchFm">
					<liferay-portlet:renderURLParams varImpl="searchURL" />
					<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
					<aui:input name="breadcrumbsCategoryId" type="hidden" value="<%= categoryId %>" />
					<aui:input name="searchCategoryId" type="hidden" value="<%= categoryId %>" />

					<liferay-ui:input-search id="keywords1" />
				</aui:form>
			</div>
		</aui:nav-bar-search>
	</c:if>
</aui:nav-bar>

<c:if test="<%= layout.isTypeControlPanel() %>">
	<div id="breadcrumb">
		<liferay-ui:breadcrumb showCurrentGroup="<%= false %>" showGuestGroup="<%= false %>" showLayout="<%= false %>" showPortletBreadcrumb="<%= true %>" />
	</div>
</c:if>