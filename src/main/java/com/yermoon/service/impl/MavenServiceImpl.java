package com.yermoon.service.impl;

import com.yermoon.dto.Maven;
import com.yermoon.service.MavenService;
import com.yermoon.utils.MyAssert;
import com.yermoon.utils.SystemUtils;
import org.springframework.stereotype.Service;

/**
 * Maven工具集业务处理默认实现类
 *
 * @author wangqing
 * @since 14-4-16 下午1:42
 */
@Service("mavenService")
public class MavenServiceImpl implements MavenService {

    @Override
    public String implLocalJar(Maven maven, String path) throws Exception {
        MyAssert.notNull(maven);
        MyAssert.notBlank(maven.getArtifactId(), maven.getGroupId(), maven.getVersion(), path);
        String cmd = "cmd /c start\n" +
                "mvn install:install-file\n" +
                "-DgroupId=" + maven.getGroupId() + "\n" +
                "-DartifactId=" + maven.getArtifactId() + " \n" +
                "-Dversion=" + maven.getVersion() + "\n" +
                "-Dfile=" + path + "\n" +
                "-Dpackaging=jar\n" +
                "-DgeneratePom=true";
        return SystemUtils.exeInWin32(cmd);
    }
}
