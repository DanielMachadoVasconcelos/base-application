CREATE TABLE IF NOT EXISTS public.summary
(
    id           varchar(36) primary key,
    country_code varchar(2) ,
    cases_number bigint      NOT NULL DEFAULT -1,
    status       varchar(20) NOT NULL,
    created_at   timestamp   default CURRENT_TIMESTAMP
);

CREATE INDEX country_code_index ON public.summary (country_code);
CREATE INDEX status_index ON public.summary (status);