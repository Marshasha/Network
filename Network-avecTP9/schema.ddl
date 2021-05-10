
    alter table Agency 
        drop constraint FK_g2xa0nfckv36dtuaf0u9buedr

    alter table Banker 
        drop constraint FK_8lyxc7gxydl4kp3yy5vodwaqu

    alter table Client 
        drop constraint FK_6nxjf59jdjxiysy7qke8l36j8

    alter table Client 
        drop constraint FK_p0n8e1o1tcj9mnnyyrsc3ywjl

    alter table Client_ExternalAccount 
        drop constraint FK_buywud696owc334c6xecu8nhc

    alter table Client_ExternalAccount 
        drop constraint FK_e8ttw557e8penbb3af90ra3xo

    alter table InternalAccount_Client 
        drop constraint FK_e1u4o0enfjvktbfxf0yw1xhrl

    alter table InternalAccount_Client 
        drop constraint FK_fdjtqarc6a0o9g1d7lxhb7wce

    alter table InternalAccount_Operation 
        drop constraint FK_ilrywki6ni8fl4j3usl8e0w3k

    alter table InternalAccount_Operation 
        drop constraint FK_g6ew1iy2jo6i4kmngvyr965yo

    drop table Address if exists

    drop table Agency if exists

    drop table Banker if exists

    drop table Client if exists

    drop table Client_ExternalAccount if exists

    drop table ExternalAccount if exists

    drop table InternalAccount if exists

    drop table InternalAccount_Client if exists

    drop table InternalAccount_Operation if exists

    drop table Operation if exists

    drop sequence hibernate_sequence

    create table Address (
        id bigint not null,
        city varchar(255),
        postalCode varchar(255),
        street varchar(255),
        primary key (id)
    )

    create table Agency (
        id bigint not null,
        address_id bigint,
        primary key (id)
    )

    create table Banker (
        id bigint not null,
        email varchar(255),
        firstname varchar(255),
        lastname varchar(255),
        agency_id bigint,
        primary key (id)
    )

    create table Client (
        id bigint not null,
        description varchar(255),
        firstname varchar(255),
        lastname varchar(255),
        address_id bigint,
        agency_id bigint,
        primary key (id)
    )

    create table Client_ExternalAccount (
        Client_id bigint not null,
        externalAccounts_id bigint not null,
        primary key (Client_id, externalAccounts_id)
    )

    create table ExternalAccount (
        id bigint not null,
        description varchar(255),
        socialNumber varchar(255),
        primary key (id)
    )

    create table InternalAccount (
        id bigint not null,
        description varchar(255),
        number varchar(255),
        saldo float not null,
        primary key (id)
    )

    create table InternalAccount_Client (
        internalAccounts_id bigint not null,
        owners_id bigint not null,
        primary key (internalAccounts_id, owners_id)
    )

    create table InternalAccount_Operation (
        InternalAccount_id bigint not null,
        operations_id bigint not null,
        primary key (InternalAccount_id, operations_id)
    )

    create table Operation (
        id bigint not null,
        amount bigint not null,
        date timestamp,
        description varchar(255),
        primary key (id)
    )

    alter table Client_ExternalAccount 
        add constraint UK_buywud696owc334c6xecu8nhc  unique (externalAccounts_id)

    alter table InternalAccount_Operation 
        add constraint UK_ilrywki6ni8fl4j3usl8e0w3k  unique (operations_id)

    alter table Agency 
        add constraint FK_g2xa0nfckv36dtuaf0u9buedr 
        foreign key (address_id) 
        references Address

    alter table Banker 
        add constraint FK_8lyxc7gxydl4kp3yy5vodwaqu 
        foreign key (agency_id) 
        references Agency

    alter table Client 
        add constraint FK_6nxjf59jdjxiysy7qke8l36j8 
        foreign key (address_id) 
        references Address

    alter table Client 
        add constraint FK_p0n8e1o1tcj9mnnyyrsc3ywjl 
        foreign key (agency_id) 
        references Agency

    alter table Client_ExternalAccount 
        add constraint FK_buywud696owc334c6xecu8nhc 
        foreign key (externalAccounts_id) 
        references ExternalAccount

    alter table Client_ExternalAccount 
        add constraint FK_e8ttw557e8penbb3af90ra3xo 
        foreign key (Client_id) 
        references Client

    alter table InternalAccount_Client 
        add constraint FK_e1u4o0enfjvktbfxf0yw1xhrl 
        foreign key (owners_id) 
        references Client

    alter table InternalAccount_Client 
        add constraint FK_fdjtqarc6a0o9g1d7lxhb7wce 
        foreign key (internalAccounts_id) 
        references InternalAccount

    alter table InternalAccount_Operation 
        add constraint FK_ilrywki6ni8fl4j3usl8e0w3k 
        foreign key (operations_id) 
        references Operation

    alter table InternalAccount_Operation 
        add constraint FK_g6ew1iy2jo6i4kmngvyr965yo 
        foreign key (InternalAccount_id) 
        references InternalAccount

    create sequence hibernate_sequence start with 1
