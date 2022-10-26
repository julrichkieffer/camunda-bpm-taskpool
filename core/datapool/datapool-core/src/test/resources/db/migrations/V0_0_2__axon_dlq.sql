CREATE TABLE dead_letter_entry (
    dead_letter_id       VARCHAR(255) NOT NULL,
    cause_message        VARCHAR(255),
    cause_type           VARCHAR(255),
    diagnostics          BLOB,
    enqueued_at          TIMESTAMP    NOT NULL,
    last_touched         TIMESTAMP,
    aggregate_identifier VARCHAR(255),
    event_identifier     VARCHAR(255) NOT NULL,
    message_type         VARCHAR(255) NOT NULL,
    meta_data            BLOB,
    payload              BLOB         NOT NULL,
    payload_revision     VARCHAR(255),
    payload_type         VARCHAR(255) NOT NULL,
    sequence_number      INT8,
    time_stamp           VARCHAR(255) NOT NULL,
    token                BLOB,
    token_type           VARCHAR(255),
    type                 VARCHAR(255),
    processing_group     VARCHAR(255) NOT NULL,
    processing_started   TIMESTAMP,
    sequence_identifier  VARCHAR(255) NOT NULL,
    sequence_index       INT8         NOT NULL,
    PRIMARY KEY (dead_letter_id)
);