--------------------------------------------------------
--  DDL for Sequence CUSTOMER_ID_SEQ
--------------------------------------------------------

CREATE SEQUENCE  "CUSTOMER_ID_SEQ"  MINVALUE 1 INCREMENT BY 1 CACHE 10 NOCYCLE;

--------------------------------------------------------
--  DDL for Table CUSTOMER
--------------------------------------------------------

CREATE TABLE "CUSTOMER" (
    "ID" NUMBER NOT NULL,
    "NAME" VARCHAR2(256 BYTE) NOT NULL,
    "STATUS" NUMBER DEFAULT 0 NOT NULL,
    CONSTRAINT "CUSTOMER_PK" PRIMARY KEY ("ID")
);

--------------------------------------------------------
--  DDL for Trigger CUSTOMER_BI
--------------------------------------------------------

CREATE OR REPLACE TRIGGER "CUSTOMER_BI"
    before insert on "CUSTOMER"
    for each row
begin  
    if inserting then
        if :NEW."ID" is null then
            select CUSTOMER_ID_SEQ.nextval into :NEW."ID" from dual;
        end if;
    end if;
end;

/

ALTER TRIGGER "CUSTOMER_BI" ENABLE;
