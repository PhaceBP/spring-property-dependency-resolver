package hu.ericsson.spring.importselector.module.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class ModuleImportSelector implements DeferredImportSelector, BeanFactoryAware {

	private BeanFactory factory;
	
	private final String PACKAGE_NAME="hu.ericsson.spring.importselector.module.config";
	

	public String[] selectImports(AnnotationMetadata importingClassMetadata) {

		ConfigurableListableBeanFactory beanFactory = (ConfigurableListableBeanFactory) factory;

		String[] configurationBeanNames = beanFactory.getBeanNamesForAnnotation(Configuration.class);

		String beanName = searchIndependentConfigBean(configurationBeanNames,null);

		String calculatedBeanName = PACKAGE_NAME+ "." +beanName.substring(0, 1).toUpperCase() + beanName.substring(1);
		
		return new String[] { calculatedBeanName };

	}

	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.factory = beanFactory;

	}

	private String searchIndependentConfigBean(String[] actualDependencies, String actualIndependentBeanName) {

		String retValue = actualIndependentBeanName;
		
		if (actualDependencies == null || actualDependencies.length == 0) {
			return actualIndependentBeanName;
		} 
		else {
			
			ConfigurableListableBeanFactory beanFactory = (ConfigurableListableBeanFactory) factory;

			for (String actualConfigBean : actualDependencies) {

				BeanDefinition actualBeanDef = beanFactory.getBeanDefinition(actualConfigBean);

				String[] dependentConfigurations = actualBeanDef.getDependsOn();

				retValue = searchIndependentConfigBean(dependentConfigurations, actualConfigBean);
			}
			
			return retValue;
		}

	}

}
