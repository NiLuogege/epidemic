package com.songyuan.epidemic.net;

import java.io.IOException;
import java.lang.reflect.Type;

import rxhttp.wrapper.annotation.Parser;
import rxhttp.wrapper.entity.ParameterizedTypeImpl;
import rxhttp.wrapper.exception.ParseException;
import rxhttp.wrapper.parse.AbstractParser;

/**
 * 通过@Parser注解，为解析器取别名为Response，此时就会在RxHttp类生成asResponse(Class<T>)方法
 *
 * @param <T>
 */
@Parser(name = "BaseResponse")
public class ResponseParser<T> extends AbstractParser<T> {

    public ResponseParser() {
        super();
    }

    public ResponseParser(Type type) {
        super(type);
    }
    @Override
    public T onParse(okhttp3.Response response) throws IOException {
        final Type type = ParameterizedTypeImpl.get(BaseResponse.class, mType); //获取泛型类型
        BaseResponse<T> data = convert(response, type);
        T t = data.getData(); //获取data字段
        if (data.getCode() != 0 || t == null) {//这里假设code不等于0，代表数据不正确，抛出异常
            throw new ParseException(String.valueOf(data.getCode()), data.getMsg(), response);
        }
        return t;
    }
}