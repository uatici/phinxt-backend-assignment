package com.phinxt.homeassignment.model.cleaner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MopCleaner implements Cleaner {
    @Override
    public void clean() {
        log.info("Patch cleaned with Mop");
    }
}
