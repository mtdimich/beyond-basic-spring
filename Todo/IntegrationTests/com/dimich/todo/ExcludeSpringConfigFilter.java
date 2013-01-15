package com.dimich.todo;

import java.io.IOException;

import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

public class ExcludeSpringConfigFilter implements TypeFilter {

	@Override
	public boolean match(MetadataReader reader, MetadataReaderFactory readerFactory) throws IOException {
		if (reader.getClassMetadata().getClassName().contains("SpringConfig")) {
			return true;
		}
		return false;
	}

}
