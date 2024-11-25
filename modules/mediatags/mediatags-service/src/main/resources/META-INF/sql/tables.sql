create table ongc_Tag (
	uuid_ VARCHAR(75) null,
	tagId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	tagName VARCHAR(75) null
);

create table ongc_Tags (
	uuid_ VARCHAR(75) null,
	tagId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	tagName VARCHAR(75) null
);