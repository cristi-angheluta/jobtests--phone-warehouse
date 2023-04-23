create table PHONES
(
    ID       BIGINT auto_increment,
    MODEL    CHARACTER VARYING not null,
    QUANTITY INTEGER           not null,
    constraint PHONES_PK
        primary key (ID),
    constraint quantity_ge_0
        check ("QUANTITY" >= 0)
);

comment on constraint quantity_ge_0 is 'quantity should be equal or greater than zero';

create table BOOKINGS
(
    ID           varchar2                            NOT NULL,
    PHONE_ID     BIGINT                              not null,
    BOOKING_DATE TIMESTAMP default CURRENT_TIMESTAMP not null,
    BOOKED_BY    CHARACTER VARYING                   not null,
    RETURN_DATE  TIMESTAMP,
    constraint BOOKINGS_PK
        primary key (ID),
    constraint BOOKINGS_PHONES_ID_FK
        foreign key (PHONE_ID) references PHONES
);
comment on column BOOKINGS.ID is 'booking-id';

create index BOOKINGS_phone_id_return_date_index
    on BOOKINGS (PHONE_ID, RETURN_DATE);

CREATE VIEW PHONES_NOT_RETURNED (PHONE_ID, BOOKED)
AS
SELECT b.PHONE_ID,
       COUNT(*) AS BOOKED
FROM BOOKINGS b
WHERE RETURN_DATE IS NULL
GROUP BY b.PHONE_ID;
