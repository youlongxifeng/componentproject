package com.component.preject.youlong.main.rx;

import com.component.preject.youlong.main.bean.BaseResponse;
import com.component.preject.youlong.rx.ApiException;
import io.reactivex.functions.Function;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/6 17:10
 * @description: （添加一句描述）
 */
public class MapFunction<F> implements Function<BaseResponse<F>, F> {
    @Override
    public F apply(BaseResponse<F> fResponseBean) throws Exception {
        int code = fResponseBean.getErrorCode();
        if (code == BaseResponse.CODE_SUCCESS || code == BaseResponse.CODE_SUCCESS0) {
            return fResponseBean.getData();
        } else {
            throw new ApiException(fResponseBean.getErrorMsg(), fResponseBean.getErrorCode());
        }
    }
}
