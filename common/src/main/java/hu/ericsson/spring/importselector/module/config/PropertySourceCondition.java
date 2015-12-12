package hu.ericsson.spring.importselector.module.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.StandardMethodMetadata;

public class PropertySourceCondition implements Condition {

	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

		StandardMethodMetadata metaDataVisitor = (StandardMethodMetadata) metadata;
		ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();

		String declaredClassName = metaDataVisitor.getDeclaringClassName();

		String beanName = declaredClassName.substring(declaredClassName.lastIndexOf('.') + 1, declaredClassName.length());

		BeanDefinition actualBeanDef = beanFactory.getBeanDefinition(beanName.substring(0, 1).toLowerCase() + beanName.substring(1));

		String[] dependentConfigurations = actualBeanDef.getDependsOn();

		if (dependentConfigurations == null || dependentConfigurations.length == 0) {
			return true;
		}

		return false;

	}

}
