CREATE TABLE IF NOT EXISTS public.summary(
    country_code varchar(2) primary key,
    country varchar(50) UNIQUE ,
    cases_number bigint NOT NULL DEFAULT -1,
    status varchar(20) NOT NULL,
    create_at timestamp default CURRENT_TIMESTAMP
);

CREATE UNIQUE INDEX country_code_index
ON public.summary (country_code);