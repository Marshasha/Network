
    alter table Computer 
        drop constraint FK_qgb1mhnm3sxnli5ck5ovimc7t

    alter table MobilePhone 
        drop constraint FK_snhetypsx230qgb3pa9f0a1qt

    alter table Tablet 
        drop constraint FK_dw7awc7nbiahxb0gyyf4bpt5s

    drop table Computer if exists

    drop table MobilePhone if exists

    drop table OperationalSystem if exists

    drop table Tablet if exists

    drop table User if exists

    drop sequence hibernate_sequence

    create table Computer (
        id bigint not null,
        osComputer_osId bigint,
        primary key (id)
    )

    create table MobilePhone (
        id bigint not null,
        osMobPhon_osId bigint,
        primary key (id)
    )

    create table OperationalSystem (
        osId bigint not null,
        operationalSystemName varchar(255),
        primary key (osId)
    )

    create table Tablet (
        id bigint not null,
        osTablet_osId bigint,
        primary key (id)
    )

    create table User (
        userId bigint not null,
        loginName varchar(255),
        name varchar(255),
        primary key (userId)
    )

    alter table Computer 
        add constraint FK_qgb1mhnm3sxnli5ck5ovimc7t 
        foreign key (osComputer_osId) 
        references OperationalSystem

    alter table MobilePhone 
        add constraint FK_snhetypsx230qgb3pa9f0a1qt 
        foreign key (osMobPhon_osId) 
        references OperationalSystem

    alter table Tablet 
        add constraint FK_dw7awc7nbiahxb0gyyf4bpt5s 
        foreign key (osTablet_osId) 
        references OperationalSystem

    create sequence hibernate_sequence start with 1
