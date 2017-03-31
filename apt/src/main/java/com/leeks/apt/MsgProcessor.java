package com.leeks.apt;

import com.google.auto.service.AutoService;
import com.leeks.apt.annotation.Bind;
import com.leeks.apt.annotation.OnMsg;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

/**
 * Created by herr.wang on 2017/3/24.
 */

@AutoService(Processor.class)
public final class MsgProcessor extends AbstractProcessor {
    private Elements elementsUtil;
    private Types typeUtil;
    private Filer filer;
    private ProcessingEnvironment processingEnvironment;
    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        this.processingEnvironment = processingEnvironment;
        elementsUtil = processingEnvironment.getElementUtils();
        typeUtil = processingEnvironment.getTypeUtils();
        filer = processingEnvironment.getFiler();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Messager messager = processingEnvironment.getMessager();
        for(Element element : roundEnvironment.getElementsAnnotatedWith(OnMsg.class)){
            messager.printMessage(Diagnostic.Kind.NOTE, element.getEnclosingElement().getSimpleName());
            int[] cmds = element.getAnnotation(OnMsg.class).cmds();
            for(int cmd : cmds){
                messager.printMessage(Diagnostic.Kind.NOTE, "cmd is " + cmd);
            }
        }
        for(Element element : roundEnvironment.getElementsAnnotatedWith(Bind.class)){
            messager.printMessage(Diagnostic.Kind.NOTE, element.getSimpleName());
            messager.printMessage(Diagnostic.Kind.NOTE, element.getAnnotation(Bind.class).name());
        }
        return false;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotationTypes = new LinkedHashSet<>();
        for(Class annotation : getSupportedAnnotations()){
            annotationTypes.add(annotation.getCanonicalName());
        }
        return annotationTypes;
    }

    public Set<Class<? extends Annotation>> getSupportedAnnotations(){
        Set<Class<? extends Annotation>> annotations = new LinkedHashSet<>();
        annotations.add(OnMsg.class);
        annotations.add(Bind.class);
        return annotations;
    }
}
