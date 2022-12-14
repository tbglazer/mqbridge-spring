package il.co.fibi.comm.mqbridge.data;

import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.w3c.dom.Document;

import co.elastic.apm.opentracing.ElasticApmTags;
import io.opentracing.Span;
import io.opentracing.util.GlobalTracer;
import lombok.extern.slf4j.Slf4j;
import net.sf.cb2xml.convert.MainframeToXml;
import net.sf.cb2xml.convert.XmlToMainframe;
import net.sf.cb2xml.util.XmlUtils;

@Component
@RequestScope
@Slf4j
public class MainframeConvertor {
	@Autowired
	MainframeDecorator decorator;

	public String xml2data(String input, String copybook) {
		final Span span = GlobalTracer.get().buildSpan("xml>data").withTag(ElasticApmTags.TYPE, "data")
				.withTag(ElasticApmTags.SUBTYPE, "xml>data").start();
		try {
			if (input != null) {
				DocumentBuilderFactory builder = DocumentBuilderFactory.newInstance();
				Document doc1 = builder.newDocumentBuilder().parse(IOUtils.toInputStream(input, "UTF-8"));
				Document doc2 = builder.newDocumentBuilder().parse(IOUtils.toInputStream(copybook, "UTF-8"));
				return new XmlToMainframe().convert(doc1, doc2, decorator);
			}
			span.setTag(ElasticApmTags.RESULT, "success");
		} catch (Exception e) {
			log.error("xml2data failed", e);
			span.setTag(ElasticApmTags.RESULT, "failure");
		} finally {
			span.finish();
		}
		return null;
	}

	public String data2xml(String input, String copybook) {
		final Span span = GlobalTracer.get().buildSpan("data>xml").withTag(ElasticApmTags.TYPE, "data")
				.withTag(ElasticApmTags.SUBTYPE, "data>xml").start();
		try {
			if (input != null) {
				DocumentBuilderFactory builder = DocumentBuilderFactory.newInstance();
				Document doc2 = builder.newDocumentBuilder().parse(IOUtils.toInputStream(copybook, "UTF-8"));
				return XmlUtils.domToString(new MainframeToXml().convert(input, doc2, decorator)).toString();
			}
			span.setTag(ElasticApmTags.RESULT, "success");
		} catch (Exception e) {
			log.error("data2xml failed", e);
			span.setTag(ElasticApmTags.RESULT, "failure");
		} finally {
			span.finish();
		}
		return null;
	}
}
