create index IX_E10DC970 on ongc_Tag (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_959EBF2 on ongc_Tag (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_DF4466B7 on ongc_Tags (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_19552EF9 on ongc_Tags (uuid_[$COLUMN_LENGTH:75$], groupId);