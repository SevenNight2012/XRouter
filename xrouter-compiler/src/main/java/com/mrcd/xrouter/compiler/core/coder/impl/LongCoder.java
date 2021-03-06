package com.mrcd.xrouter.compiler.core.coder.impl;

import com.mrcd.xrouter.compiler.core.coder.ITypeCoder;

public class LongCoder implements ITypeCoder {

    @Override
    public String generateCode(String fieldName, String key) {
        return "target." + fieldName + " = wrapper.getLong(intent, \"" + key + "\")";
    }

    @Override
    public String releaseCode(String fieldName, String key) {
        return "";
    }
}
