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
    ID           BIGINT auto_increment,
    PID          BIGINT                              not null,
    BOOKING_DATE TIMESTAMP default CURRENT_TIMESTAMP not null,
    BOOKED_BY    CHARACTER VARYING                   not null,
    RETURN_DATE  TIMESTAMP,
    constraint BOOKINGS_PK
        primary key (ID),
    constraint BOOKINGS_PHONES_ID_FK
        foreign key (PID) references PHONES
);
comment on column BOOKINGS.ID is 'booking-id';

create index BOOKINGS_pid_return_date_index
    on BOOKINGS (PID, RETURN_DATE);

CREATE VIEW PHONES_NOT_RETURNED (PID, BOOKED)
AS
SELECT b.PID,
       COUNT(*) AS BOOKED
FROM BOOKINGS b
WHERE RETURN_DATE IS NULL
GROUP BY b.PID;
