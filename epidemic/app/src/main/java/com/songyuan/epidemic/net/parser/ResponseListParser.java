package com.songyuan.epidemic.net.parser;

import com.songyuan.epidemic.net.BaseResponse;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import rxhttp.wrapper.annotation.Parser;
import rxhttp.wrapper.entity.ParameterizedTypeImpl;
import rxhttp.wrapper.exception.ParseException;
import rxhttp.wrapper.parse.AbstractParser;

@Parser(name = "ResponseList")
public class ResponseListParser<T> extends AbstractParser<List<T>> {

    public ResponseListParser() {
        super();
    }

    public ResponseListParser(Type type) {
        super(type);
    }

    @Override
    public List<T> onParse(okhttp3.Response response) throws IOException {
        final Type type = ParameterizedTypeImpl.get(BaseResponse.class, List.class, mType); //获取泛型类型
        BaseResponse<List<T>> data = convert(response, type);
        List<T> list = data.getAttachment(); //获取data字段
        if (data.getErrorCode() != 0 || list == null) {  //code不等于0，说明数据不正确，抛出异常
            throw new ParseException(String.valueOf(data.getErrorCode()), data.getMessage(), response);
        }
        return list;
    }
}