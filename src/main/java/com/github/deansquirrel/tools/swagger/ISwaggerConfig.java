package com.github.deansquirrel.tools.swagger;

import springfox.documentation.service.Tag;

public interface ISwaggerConfig {

    /**
     * 是否启用
     * @return 是否启用
     */
    Boolean getEnable();

    /**
     * 标题
     * @return 标题
     */
    String getTitle();

    /**
     * 描述
     * @return 描述
     */
    String getDescription();

    /**
     * 版本
     * @return 版本
     */
    String getVersion();

    /**
     * 扫描的包路径 如 com.yuansong，使用逗号分隔
     * @return 扫描包
     */
    String getBasePackage();
    /**
     * 获取Tag数组
     * @return Tag数组
     */
    default Tag[] getControllerTags() {
        return null;
    }

}
