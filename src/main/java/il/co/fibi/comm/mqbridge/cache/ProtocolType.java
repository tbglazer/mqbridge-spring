package il.co.fibi.comm.mqbridge.cache;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum ProtocolType {
	@XmlEnumValue("3270")
	L3270, @XmlEnumValue("DPL")
	DPL, @XmlEnumValue("CONT")
	CONT
};
