package com.colak.basemodule;

import java.util.List;

public class ModuleManager {

    public static void initializeModules(List<BaseModule> modules) {
        modules.forEach(BaseModule::initialize);
    }

    public static void startModules(List<BaseModule> modules) {
        modules.forEach(BaseModule::start);
    }

    public static void shutdownModules(List<BaseModule> modules) {
        modules.forEach(BaseModule::shutdown);
    }
}
