create table if not exists outbox_events (
    id uuid DEFAULT gen_random_uuid(),
    aggregate_id varchar not null,
    aggregate_type varchar not null,
    aggregate_sub_type varchar not null,
    event_timestamp bigint not null,
    payload bytea not null,
    primary key (id),
    unique (aggregate_id)
);