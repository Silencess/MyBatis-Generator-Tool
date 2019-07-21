package org.plum.generator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.CommentGeneratorConfiguration;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.config.JavaClientGeneratorConfiguration;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;
import org.mybatis.generator.config.JavaTypeResolverConfiguration;
import org.mybatis.generator.config.ModelType;
import org.mybatis.generator.config.SqlMapGeneratorConfiguration;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.internal.DefaultShellCallback;

public class GeneratorHelper {
	private Context context;
	private String classpathEntry;
	
	public void setClasspathEntry(String classpathEntry) {
		this.classpathEntry = classpathEntry;
	}

	public void createCommentGeneratorConfiguration(String suppressAllComments) {
		CommentGeneratorConfiguration commentGeneratorConfiguration = new CommentGeneratorConfiguration();
		commentGeneratorConfiguration.addProperty("suppressAllComments", suppressAllComments);
		context.setCommentGeneratorConfiguration(commentGeneratorConfiguration);
		
	}
	public void createContext(String defaultModelType, String targetRuntime) {
		ModelType modelType = ModelType.getModelType(defaultModelType);
		context = new Context(modelType);
		context.setTargetRuntime(targetRuntime);
		context.setId("test");
	}

	public void createJavaTypeResolverConfiguration(String forceBigDecimals) {
		JavaTypeResolverConfiguration javaTypeResolverConfiguration = new JavaTypeResolverConfiguration();
		javaTypeResolverConfiguration.addProperty("forceBigDecimals", forceBigDecimals);
		context.setJavaTypeResolverConfiguration(javaTypeResolverConfiguration);
	}

	public void createJavaModelGenerator(Map<String, String> javaModelMap) {
		JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
		javaModelGeneratorConfiguration.setTargetPackage(javaModelMap.get("targetPackage"));
		javaModelGeneratorConfiguration.setTargetProject(javaModelMap.get("targetProject"));
		javaModelGeneratorConfiguration.addProperty("constructorBased", javaModelMap.get("constructorBased"));
		javaModelGeneratorConfiguration.addProperty("immutable", javaModelMap.get("immutable"));
		javaModelGeneratorConfiguration.addProperty("enableSubPackages", javaModelMap.get("enableSubPackages"));
		javaModelGeneratorConfiguration.addProperty("trimStrings", javaModelMap.get("trimStrings"));
		if(javaModelMap.containsKey("rootClass")) {
			javaModelGeneratorConfiguration.addProperty("rootClass",javaModelMap.get("rootClass"));
		}
		context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);
	}

	public void createSqlMapGenerator(Map<String, String> sqlMapMap) {
		SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
		sqlMapGeneratorConfiguration.setTargetPackage(sqlMapMap.get("targetPackage"));
		sqlMapGeneratorConfiguration.setTargetProject(sqlMapMap.get("targetProject"));
		sqlMapGeneratorConfiguration.addProperty("enableSubPackages", sqlMapMap.get("enableSubPackages"));
		context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);
	}

	public void createJavaClientGenerator(Map<String, String> javaClientMap) {
		JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
		javaClientGeneratorConfiguration.setTargetPackage(javaClientMap.get("targetPackage"));
		javaClientGeneratorConfiguration.setTargetProject(javaClientMap.get("targetProject"));
		javaClientGeneratorConfiguration.addProperty("enableSubPackages", javaClientMap.get("enableSubPackages"));
		javaClientGeneratorConfiguration.setConfigurationType(javaClientMap.get("type"));
		context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);
	}

	public void createJDBCConnectionConfiguration(Map<String, String> jdbcConnectionMap) {
		JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
		jdbcConnectionConfiguration.setConnectionURL(jdbcConnectionMap.get("connectionURL"));
		jdbcConnectionConfiguration.setDriverClass(jdbcConnectionMap.get("driverClass"));
		jdbcConnectionConfiguration.setUserId(jdbcConnectionMap.get("userId"));
		jdbcConnectionConfiguration.setPassword(jdbcConnectionMap.get("password"));
		context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);
	}

	public void createTableConfiguration(String[] tableName) {
		for (String name : tableName) {
			TableConfiguration tableConfiguration = new TableConfiguration(context);
			tableConfiguration.setTableName(name);
			context.addTableConfiguration(tableConfiguration);
		}
	}

	public void generator() throws SQLException, IOException, InterruptedException, InvalidConfigurationException {
		Configuration configuration = new Configuration();
		configuration.addContext(context);
		configuration.addClasspathEntry(classpathEntry);

		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(configuration, callback, warnings);
		myBatisGenerator.generate(null);
	}
}