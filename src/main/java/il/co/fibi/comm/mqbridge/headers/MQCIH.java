package il.co.fibi.comm.mqbridge.headers;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import com.ibm.etools.marshall.util.*;

/**
 * @generated
 * Generated Class: MQCIH
 * @type-descriptor.aggregate-instance-td accessor="readWrite" contentSize="180" offset="0" size="180"
 * @type-descriptor.platform-compiler-info language="COBOL" defaultBigEndian="true" defaultCodepage="IBM-037" defaultExternalDecimalSign="ebcdic" defaultFloatType="ibm390Hex"
 */
@SuppressWarnings({"rawtypes","unused","unchecked","deprecation"})
public class MQCIH implements javax.resource.cci.Record, javax.resource.cci.Streamable, com.ibm.etools.marshall.RecordBytes {
	private static final long serialVersionUID = -7522662255995187763L;
	/**
	 * @generated
	 */
	private byte[] buffer_ = null;
	/**
	 * @generated
	 */
	private static final int bufferSize_;
	/**
	 * @generated
	 */
	private static final byte[] initializedBuffer_;
	/**
	 * @generated
	 */
	private static java.util.HashMap getterMap_ = null;
	/**
	 * @generated
	 */
	private java.util.HashMap valFieldNameMap_ = null;

	/**
	 * initializer
	 * @generated
	 */
	static {
		bufferSize_ = 180;
		initializedBuffer_ = new byte[bufferSize_];
		String mqcih__facilityInitialValue = " ";
		MarshallStringUtils.marshallFixedLengthStringIntoBuffer(mqcih__facilityInitialValue, initializedBuffer_, 76, "IBM-037", 8, MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
		String mqcih__remotesysidInitialValue = " ";
		MarshallStringUtils.marshallFixedLengthStringIntoBuffer(mqcih__remotesysidInitialValue, initializedBuffer_, 116, "IBM-037", 4, MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
		String mqcih__cancelcodeInitialValue = " ";
		MarshallStringUtils.marshallFixedLengthStringIntoBuffer(mqcih__cancelcodeInitialValue, initializedBuffer_, 140, "IBM-037", 4, MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
		String mqcih__nexttransactionidInitialValue = " ";
		MarshallStringUtils.marshallFixedLengthStringIntoBuffer(mqcih__nexttransactionidInitialValue, initializedBuffer_, 144, "IBM-037", 4, MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
		String mqcih__formatInitialValue = " ";
		MarshallStringUtils.marshallFixedLengthStringIntoBuffer(mqcih__formatInitialValue, initializedBuffer_, 20, "IBM-037", 8, MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
		String mqcih__authenticatorInitialValue = " ";
		MarshallStringUtils.marshallFixedLengthStringIntoBuffer(mqcih__authenticatorInitialValue, initializedBuffer_, 92, "IBM-037", 8, MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
		String mqcih__facilitylikeInitialValue = " ";
		MarshallStringUtils.marshallFixedLengthStringIntoBuffer(mqcih__facilitylikeInitialValue, initializedBuffer_, 128, "IBM-037", 4, MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
		String mqcih__attentionidInitialValue = " ";
		MarshallStringUtils.marshallFixedLengthStringIntoBuffer(mqcih__attentionidInitialValue, initializedBuffer_, 132, "IBM-037", 4, MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
		String mqcih__transactionidInitialValue = " ";
		MarshallStringUtils.marshallFixedLengthStringIntoBuffer(mqcih__transactionidInitialValue, initializedBuffer_, 124, "IBM-037", 4, MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
		String mqcih__remotetransidInitialValue = " ";
		MarshallStringUtils.marshallFixedLengthStringIntoBuffer(mqcih__remotetransidInitialValue, initializedBuffer_, 120, "IBM-037", 4, MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
		String mqcih__abendcodeInitialValue = " ";
		MarshallStringUtils.marshallFixedLengthStringIntoBuffer(mqcih__abendcodeInitialValue, initializedBuffer_, 88, "IBM-037", 4, MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
		String mqcih__reserved1InitialValue = " ";
		MarshallStringUtils.marshallFixedLengthStringIntoBuffer(mqcih__reserved1InitialValue, initializedBuffer_, 100, "IBM-037", 8, MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
		String mqcih__reserved2InitialValue = " ";
		MarshallStringUtils.marshallFixedLengthStringIntoBuffer(mqcih__reserved2InitialValue, initializedBuffer_, 148, "IBM-037", 8, MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
		String mqcih__reserved3InitialValue = " ";
		MarshallStringUtils.marshallFixedLengthStringIntoBuffer(mqcih__reserved3InitialValue, initializedBuffer_, 156, "IBM-037", 8, MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
		String mqcih__startcodeInitialValue = " ";
		MarshallStringUtils.marshallFixedLengthStringIntoBuffer(mqcih__startcodeInitialValue, initializedBuffer_, 136, "IBM-037", 4, MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
		String mqcih__functionInitialValue = " ";
		MarshallStringUtils.marshallFixedLengthStringIntoBuffer(mqcih__functionInitialValue, initializedBuffer_, 84, "IBM-037", 4, MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
		String mqcih__strucidInitialValue = " ";
		MarshallStringUtils.marshallFixedLengthStringIntoBuffer(mqcih__strucidInitialValue, initializedBuffer_, 0, "IBM-037", 4, MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
		String mqcih__replytoformatInitialValue = " ";
		MarshallStringUtils.marshallFixedLengthStringIntoBuffer(mqcih__replytoformatInitialValue, initializedBuffer_, 108, "IBM-037", 8, MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
	}

	/**
	 * constructor
	 * @generated
	 */
	public MQCIH() {
		initialize();
	}

	/**
	 * constructor
	 * @generated
	 */
	public MQCIH(java.util.HashMap valFieldNameMap) {
		valFieldNameMap_ = valFieldNameMap;
		initialize();
	}

	/**
	 * @generated
	 * initialize
	 */
	public void initialize() {
		buffer_ = new byte[bufferSize_];
		System.arraycopy(initializedBuffer_, 0, buffer_, 0, bufferSize_);
	}

	/**
	 * @generated
	 * @see javax.resource.cci.Streamable#read(java.io.InputStream)
	 */
	public void read(java.io.InputStream inputStream) throws java.io.IOException {
		byte[] input = new byte[inputStream.available()];
		inputStream.read(input);
		buffer_ = input;
	}

	/**
	 * @generated
	 * @see javax.resource.cci.Streamable#write(java.io.OutputStream)
	 */
	public void write(java.io.OutputStream outputStream) throws java.io.IOException {
		outputStream.write(buffer_, 0, getSize());
	}

	/**
	 * @generated
	 * @see javax.resource.cci.Record#getRecordName()
	 */
	public String getRecordName() {
		return (this.getClass().getName());
	}

	/**
	 * @generated
	 * @see javax.resource.cci.Record#setRecordName(String)
	 */
	public void setRecordName(String recordName) {
		return;
	}

	/**
	 * @generated
	 * @see javax.resource.cci.Record#setRecordShortDescription(String)
	 */
	public void setRecordShortDescription(String shortDescription) {
		return;
	}

	/**
	 * @generated
	 * @see javax.resource.cci.Record#getRecordShortDescription()
	 */
	public String getRecordShortDescription() {
		return (this.getClass().getName());
	}

	/**
	 * @generated
	 * @see javax.resource.cci.Record#clone()
	 */
	public Object clone() throws CloneNotSupportedException {
		return (super.clone());
	}

	/**
	 * @generated
	 * @see javax.resource.cci.Record#equals
	 */
	public boolean equals(Object object) {
		return (super.equals(object));
	}

	/**
	 * @generated
	 * @see javax.resource.cci.Record#hashCode
	 */
	public int hashCode() {
		return (super.hashCode());
	}

	/**
	 * @generated
	 * @see com.ibm.etools.marshall.RecordBytes#getBytes
	 */
	public byte[] getBytes() {
		return (buffer_);
	}

	/**
	 * @generated
	 * @see com.ibm.etools.marshall.RecordBytes#setBytes
	 */
	public void setBytes(byte[] bytes) {
		if ((bytes != null) && (bytes.length != 0))
			buffer_ = bytes;
	}

	/**
	 * @generated
	 * @see com.ibm.etools.marshall.RecordBytes#getSize
	 */
	public int getSize() {
		return (180);
	}

	/**
	 * @generated
	 */
	public boolean match(Object obj) {
		if (obj == null)
			return (false);
		if (obj.getClass().isArray()) {
			byte[] currBytes = buffer_;
			try {
				byte[] objByteArray = (byte[]) obj;
				if (objByteArray.length != buffer_.length)
					return (false);
				buffer_ = objByteArray;
			}
			catch (ClassCastException exc) {
				return (false);
			}
			finally {
				buffer_ = currBytes;
			}
		}
		else
			return (false);
		return (true);
	}

	/**
	 * @generated
	 */
	public void populate(Object obj) {
		if (obj.getClass().isArray()) {
			try {
				buffer_ = (byte[]) obj;
			}
			catch (ClassCastException exc) {
			}
		}
	}

	/**
	 * @generated
	 * @see java.lang.Object#toString
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer(super.toString());
		sb.append("\n");
		ConversionUtils.dumpBytes(sb, buffer_);
		return (sb.toString());
	}

	/**
	 * @generated
	 * wrappedGetNumber
	 */
	public Number wrappedGetNumber(String propertyName) {
		Number result = null;

		if (getterMap_ == null) {
			synchronized (initializedBuffer_) {
				if (getterMap_ == null) {
					java.util.HashMap getterMap = new java.util.HashMap();
					try {
						BeanInfo info = Introspector.getBeanInfo(this.getClass());
						PropertyDescriptor[] props = info.getPropertyDescriptors();

						for (int i = 0; i < props.length; i++) {
							String propName = props[i].getName();
							getterMap.put(propName, props[i].getReadMethod());
						}
					}
					catch (IntrospectionException exc) {
					}
					getterMap_ = getterMap;
				}
			}
		}

		Method method = (Method) getterMap_.get(propertyName);
		if (method != null) {
			try {
				result = (Number) method.invoke(this, new Object[0]);
			}
			catch (Exception exc) {
			}
		}

		return (result);
	}

	/**
	 * @generated
	 * evaluateMap
	 */
	public java.util.HashMap evaluateMap(java.util.HashMap valFieldNameMap) {
		if (valFieldNameMap == null)
			return (null);
		java.util.HashMap returnMap = new java.util.HashMap(valFieldNameMap.size());
		java.util.Set aSet = valFieldNameMap.entrySet();

		for (java.util.Iterator cursor = aSet.iterator(); cursor.hasNext();) {
			java.util.Map.Entry element = (java.util.Map.Entry) cursor.next();
			String key = (String) element.getKey();
			String fieldName = (String) element.getValue();
			Number fieldValue = wrappedGetNumber(fieldName);
			if (fieldValue == null)
				fieldValue = new Integer(0);
			returnMap.put(key, fieldValue);
		}

		return (returnMap);
	}

	/**
	 * @generated
	 * Returns the integer value of the formula string for an offset or size.
	 * The formula can be comprised of the following functions:
	 * neg(x)   := -x       // prefix negate
	 * add(x,y) := x+y      // infix add
	 * sub(x,y) := x-y      // infix subtract
	 * mpy(x,y) := x*y      // infix multiply
	 * div(x,y) := x/y      // infix divide
	 * max(x,y) := max(x,y)
	 * min(x,y) := min(x,y)
	 *
	 * mod(x,y) := x mod y
	 *
	 * The mod function is defined as mod(x,y) = r where r is the smallest non-negative integer
	 * such that x-r is evenly divisible by y. So mod(7,4) is 3, but mod(-7,4) is 1. If y is a
	 * power of 2, then mod(x,y) is equal to the bitwise-and of x and y-1.
	 *
	 * val(1, m, n, o,..)
	 *
	 * The val function returns the value of a field in the model. The val function takes one
	 * or more arguments, and the first argument refers to a level-1 field in the type model and must be either:
	 *    - the name of a level-1 field described in the language model
	 *    - the integer 1 (indicating that the level-1 parent of the current structure is meant)
	 * If the first argument to the val function is the integer 1, then and only then are subsequent arguments
	 * permitted. These subsequent arguments are integers that the specify the ordinal number within its
	 * substructure of the subfield that should be dereferenced.
	 *
	 * @return The integer value of the formula string for an offset or size.
	 * @param formula The formula to be evaluated.
	 * @param valFieldNameMap A map of val() formulas to field names.
	 * @throws IllegalArgumentException if the formula is null.
	 */

	public int evaluateFormula(String formula, java.util.HashMap valFieldNameMap) throws IllegalArgumentException {
		if (formula == null)
			throw new IllegalArgumentException(MarshallResource.instance().getString(MarshallResource.MARSHRT_FORMULA_NULL));

		int result = 0;

		int index = formula.indexOf("(");

		if (index == -1) // It's a number not an expression
		{
			try {
				result = Integer.parseInt(formula);
			}
			catch (Exception exc) {
			}

			return (result);
		}

		// Determine the outermost function
		String function = formula.substring(0, index);

		if (function.equalsIgnoreCase("val")) {
			Object field = valFieldNameMap.get(formula);
			if (field == null)
				return (0);

			if (field instanceof String) {
				Number num = wrappedGetNumber((String) field);
				if (num == null) // Element does not exist
					return (0);
				result = num.intValue();
			}
			else if (field instanceof Number)
				result = ((Number) field).intValue();
			else
				return (0);

			return (result);
		}
		else if (function.equalsIgnoreCase("neg")) {
			// The new formula is the content between the brackets
			formula = formula.substring(index + 1, formula.length() - 1);
			result = -1 * evaluateFormula(formula, valFieldNameMap);
			return (result);
		}
		else {
			// Get the contents between the outermost brackets
			formula = formula.substring(index + 1, formula.length() - 1);
			char[] formulaChars = formula.toCharArray();

			// Get the left side and the right side of the operation

			int brackets = 0;
			int i = 0;

			for (; i < formulaChars.length; i++) {
				if (formulaChars[i] == '(')
					brackets++;
				else if (formulaChars[i] == ')')
					brackets--;
				else if (formulaChars[i] == ',') {
					if (brackets == 0)
						break;
				}
			}

			String leftSide = "0";
			String rightSide = "0";

			leftSide = formula.substring(0, i);
			rightSide = formula.substring(i + 1);

			if (function.equalsIgnoreCase("add"))
				result = evaluateFormula(leftSide, valFieldNameMap) + evaluateFormula(rightSide, valFieldNameMap);
			else if (function.equalsIgnoreCase("mpy"))
				result = evaluateFormula(leftSide, valFieldNameMap) * evaluateFormula(rightSide, valFieldNameMap);
			else if (function.equalsIgnoreCase("sub"))
				result = evaluateFormula(leftSide, valFieldNameMap) - evaluateFormula(rightSide, valFieldNameMap);
			else if (function.equalsIgnoreCase("div"))
				result = evaluateFormula(leftSide, valFieldNameMap) / evaluateFormula(rightSide, valFieldNameMap);
			else if (function.equalsIgnoreCase("max"))
				result = Math.max(evaluateFormula(leftSide, valFieldNameMap), evaluateFormula(rightSide, valFieldNameMap));
			else if (function.equalsIgnoreCase("min"))
				result = Math.min(evaluateFormula(leftSide, valFieldNameMap), evaluateFormula(rightSide, valFieldNameMap));
			else if (function.equalsIgnoreCase("mod"))
				result = evaluateFormula(leftSide, valFieldNameMap) % evaluateFormula(rightSide, valFieldNameMap);
		}

		return (result);
	}

	/**
	 * @generated
	 * @type-descriptor.restriction maxLength="4"
	 * @type-descriptor.initial-value kind="SPACE"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="4" offset="0" size="4"
	 * @type-descriptor.string-td characterSize="1" lengthEncoding="fixedLength" paddingCharacter=" " prefixLength="0"
	 */
	public String getMqcih__strucid() {
		String mqcih__strucid = null;
		mqcih__strucid = MarshallStringUtils.unmarshallFixedLengthStringFromBuffer(buffer_, 0, "IBM-037", 4);
		return (mqcih__strucid);
	}

	/**
	 * @generated
	 */
	public void setMqcih__strucid(String mqcih__strucid) {
		if (mqcih__strucid != null) {
			if (mqcih__strucid.length() > 4)
				throw new IllegalArgumentException(MarshallResource.instance().getString(MarshallResource.IWAA0124E, mqcih__strucid, "4", "mqcih__strucid"));
			MarshallStringUtils.marshallFixedLengthStringIntoBuffer(mqcih__strucid, buffer_, 0, "IBM-037", 4, MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
		}
	}

	/**
	 * @generated
	 * @type-descriptor.restriction lowerBound="-999999999" upperBound="999999999"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="4" offset="4" size="4"
	 * @type-descriptor.integer-td signCoding="twosComplement"
	 */
	public int getMqcih__version() {
		int mqcih__version = 0;
		mqcih__version = MarshallIntegerUtils.unmarshallFourByteIntegerFromBuffer(buffer_, 4, true, MarshallIntegerUtils.SIGN_CODING_TWOS_COMPLEMENT);
		return (mqcih__version);
	}

	/**
	 * @generated
	 */
	public void setMqcih__version(int mqcih__version) {
		if ((mqcih__version < -999999999) || (mqcih__version > 999999999))
			throw new IllegalArgumentException(MarshallResource.instance().getString(MarshallResource.IWAA0127E, Integer.toString(mqcih__version), "mqcih__version", "-999999999", "999999999"));
		MarshallIntegerUtils.marshallFourByteIntegerIntoBuffer(mqcih__version, buffer_, 4, true, MarshallIntegerUtils.SIGN_CODING_TWOS_COMPLEMENT);
	}

	/**
	 * @generated
	 * @type-descriptor.restriction lowerBound="-999999999" upperBound="999999999"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="4" offset="8" size="4"
	 * @type-descriptor.integer-td signCoding="twosComplement"
	 */
	public int getMqcih__struclength() {
		int mqcih__struclength = 0;
		mqcih__struclength = MarshallIntegerUtils.unmarshallFourByteIntegerFromBuffer(buffer_, 8, true, MarshallIntegerUtils.SIGN_CODING_TWOS_COMPLEMENT);
		return (mqcih__struclength);
	}

	/**
	 * @generated
	 */
	public void setMqcih__struclength(int mqcih__struclength) {
		if ((mqcih__struclength < -999999999) || (mqcih__struclength > 999999999))
			throw new IllegalArgumentException(MarshallResource.instance().getString(MarshallResource.IWAA0127E, Integer.toString(mqcih__struclength), "mqcih__struclength", "-999999999", "999999999"));
		MarshallIntegerUtils.marshallFourByteIntegerIntoBuffer(mqcih__struclength, buffer_, 8, true, MarshallIntegerUtils.SIGN_CODING_TWOS_COMPLEMENT);
	}

	/**
	 * @generated
	 * @type-descriptor.restriction lowerBound="-999999999" upperBound="999999999"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="4" offset="12" size="4"
	 * @type-descriptor.integer-td signCoding="twosComplement"
	 */
	public int getMqcih__encoding() {
		int mqcih__encoding = 0;
		mqcih__encoding = MarshallIntegerUtils.unmarshallFourByteIntegerFromBuffer(buffer_, 12, true, MarshallIntegerUtils.SIGN_CODING_TWOS_COMPLEMENT);
		return (mqcih__encoding);
	}

	/**
	 * @generated
	 */
	public void setMqcih__encoding(int mqcih__encoding) {
		if ((mqcih__encoding < -999999999) || (mqcih__encoding > 999999999))
			throw new IllegalArgumentException(MarshallResource.instance().getString(MarshallResource.IWAA0127E, Integer.toString(mqcih__encoding), "mqcih__encoding", "-999999999", "999999999"));
		MarshallIntegerUtils.marshallFourByteIntegerIntoBuffer(mqcih__encoding, buffer_, 12, true, MarshallIntegerUtils.SIGN_CODING_TWOS_COMPLEMENT);
	}

	/**
	 * @generated
	 * @type-descriptor.restriction lowerBound="-999999999" upperBound="999999999"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="4" offset="16" size="4"
	 * @type-descriptor.integer-td signCoding="twosComplement"
	 */
	public int getMqcih__codedcharsetid() {
		int mqcih__codedcharsetid = 0;
		mqcih__codedcharsetid = MarshallIntegerUtils.unmarshallFourByteIntegerFromBuffer(buffer_, 16, true, MarshallIntegerUtils.SIGN_CODING_TWOS_COMPLEMENT);
		return (mqcih__codedcharsetid);
	}

	/**
	 * @generated
	 */
	public void setMqcih__codedcharsetid(int mqcih__codedcharsetid) {
		if ((mqcih__codedcharsetid < -999999999) || (mqcih__codedcharsetid > 999999999))
			throw new IllegalArgumentException(MarshallResource.instance().getString(MarshallResource.IWAA0127E, Integer.toString(mqcih__codedcharsetid), "mqcih__codedcharsetid", "-999999999", "999999999"));
		MarshallIntegerUtils.marshallFourByteIntegerIntoBuffer(mqcih__codedcharsetid, buffer_, 16, true, MarshallIntegerUtils.SIGN_CODING_TWOS_COMPLEMENT);
	}

	/**
	 * @generated
	 * @type-descriptor.restriction maxLength="8"
	 * @type-descriptor.initial-value kind="SPACE"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="8" offset="20" size="8"
	 * @type-descriptor.string-td characterSize="1" lengthEncoding="fixedLength" paddingCharacter=" " prefixLength="0"
	 */
	public String getMqcih__format() {
		String mqcih__format = null;
		mqcih__format = MarshallStringUtils.unmarshallFixedLengthStringFromBuffer(buffer_, 20, "IBM-037", 8);
		return (mqcih__format);
	}

	/**
	 * @generated
	 */
	public void setMqcih__format(String mqcih__format) {
		if (mqcih__format != null) {
			if (mqcih__format.length() > 8)
				throw new IllegalArgumentException(MarshallResource.instance().getString(MarshallResource.IWAA0124E, mqcih__format, "8", "mqcih__format"));
			MarshallStringUtils.marshallFixedLengthStringIntoBuffer(mqcih__format, buffer_, 20, "IBM-037", 8, MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
		}
	}

	/**
	 * @generated
	 * @type-descriptor.restriction lowerBound="-999999999" upperBound="999999999"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="4" offset="28" size="4"
	 * @type-descriptor.integer-td signCoding="twosComplement"
	 */
	public int getMqcih__flags() {
		int mqcih__flags = 0;
		mqcih__flags = MarshallIntegerUtils.unmarshallFourByteIntegerFromBuffer(buffer_, 28, true, MarshallIntegerUtils.SIGN_CODING_TWOS_COMPLEMENT);
		return (mqcih__flags);
	}

	/**
	 * @generated
	 */
	public void setMqcih__flags(int mqcih__flags) {
		if ((mqcih__flags < -999999999) || (mqcih__flags > 999999999))
			throw new IllegalArgumentException(MarshallResource.instance().getString(MarshallResource.IWAA0127E, Integer.toString(mqcih__flags), "mqcih__flags", "-999999999", "999999999"));
		MarshallIntegerUtils.marshallFourByteIntegerIntoBuffer(mqcih__flags, buffer_, 28, true, MarshallIntegerUtils.SIGN_CODING_TWOS_COMPLEMENT);
	}

	/**
	 * @generated
	 * @type-descriptor.restriction lowerBound="-999999999" upperBound="999999999"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="4" offset="32" size="4"
	 * @type-descriptor.integer-td signCoding="twosComplement"
	 */
	public int getMqcih__returncode() {
		int mqcih__returncode = 0;
		mqcih__returncode = MarshallIntegerUtils.unmarshallFourByteIntegerFromBuffer(buffer_, 32, true, MarshallIntegerUtils.SIGN_CODING_TWOS_COMPLEMENT);
		return (mqcih__returncode);
	}

	/**
	 * @generated
	 */
	public void setMqcih__returncode(int mqcih__returncode) {
		if ((mqcih__returncode < -999999999) || (mqcih__returncode > 999999999))
			throw new IllegalArgumentException(MarshallResource.instance().getString(MarshallResource.IWAA0127E, Integer.toString(mqcih__returncode), "mqcih__returncode", "-999999999", "999999999"));
		MarshallIntegerUtils.marshallFourByteIntegerIntoBuffer(mqcih__returncode, buffer_, 32, true, MarshallIntegerUtils.SIGN_CODING_TWOS_COMPLEMENT);
	}

	/**
	 * @generated
	 * @type-descriptor.restriction lowerBound="-999999999" upperBound="999999999"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="4" offset="36" size="4"
	 * @type-descriptor.integer-td signCoding="twosComplement"
	 */
	public int getMqcih__compcode() {
		int mqcih__compcode = 0;
		mqcih__compcode = MarshallIntegerUtils.unmarshallFourByteIntegerFromBuffer(buffer_, 36, true, MarshallIntegerUtils.SIGN_CODING_TWOS_COMPLEMENT);
		return (mqcih__compcode);
	}

	/**
	 * @generated
	 */
	public void setMqcih__compcode(int mqcih__compcode) {
		if ((mqcih__compcode < -999999999) || (mqcih__compcode > 999999999))
			throw new IllegalArgumentException(MarshallResource.instance().getString(MarshallResource.IWAA0127E, Integer.toString(mqcih__compcode), "mqcih__compcode", "-999999999", "999999999"));
		MarshallIntegerUtils.marshallFourByteIntegerIntoBuffer(mqcih__compcode, buffer_, 36, true, MarshallIntegerUtils.SIGN_CODING_TWOS_COMPLEMENT);
	}

	/**
	 * @generated
	 * @type-descriptor.restriction lowerBound="-999999999" upperBound="999999999"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="4" offset="40" size="4"
	 * @type-descriptor.integer-td signCoding="twosComplement"
	 */
	public int getMqcih__reason() {
		int mqcih__reason = 0;
		mqcih__reason = MarshallIntegerUtils.unmarshallFourByteIntegerFromBuffer(buffer_, 40, true, MarshallIntegerUtils.SIGN_CODING_TWOS_COMPLEMENT);
		return (mqcih__reason);
	}

	/**
	 * @generated
	 */
	public void setMqcih__reason(int mqcih__reason) {
		if ((mqcih__reason < -999999999) || (mqcih__reason > 999999999))
			throw new IllegalArgumentException(MarshallResource.instance().getString(MarshallResource.IWAA0127E, Integer.toString(mqcih__reason), "mqcih__reason", "-999999999", "999999999"));
		MarshallIntegerUtils.marshallFourByteIntegerIntoBuffer(mqcih__reason, buffer_, 40, true, MarshallIntegerUtils.SIGN_CODING_TWOS_COMPLEMENT);
	}

	/**
	 * @generated
	 * @type-descriptor.restriction lowerBound="-999999999" upperBound="999999999"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="4" offset="44" size="4"
	 * @type-descriptor.integer-td signCoding="twosComplement"
	 */
	public int getMqcih__uowcontrol() {
		int mqcih__uowcontrol = 0;
		mqcih__uowcontrol = MarshallIntegerUtils.unmarshallFourByteIntegerFromBuffer(buffer_, 44, true, MarshallIntegerUtils.SIGN_CODING_TWOS_COMPLEMENT);
		return (mqcih__uowcontrol);
	}

	/**
	 * @generated
	 */
	public void setMqcih__uowcontrol(int mqcih__uowcontrol) {
		if ((mqcih__uowcontrol < -999999999) || (mqcih__uowcontrol > 999999999))
			throw new IllegalArgumentException(MarshallResource.instance().getString(MarshallResource.IWAA0127E, Integer.toString(mqcih__uowcontrol), "mqcih__uowcontrol", "-999999999", "999999999"));
		MarshallIntegerUtils.marshallFourByteIntegerIntoBuffer(mqcih__uowcontrol, buffer_, 44, true, MarshallIntegerUtils.SIGN_CODING_TWOS_COMPLEMENT);
	}

	/**
	 * @generated
	 * @type-descriptor.restriction lowerBound="-999999999" upperBound="999999999"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="4" offset="48" size="4"
	 * @type-descriptor.integer-td signCoding="twosComplement"
	 */
	public int getMqcih__getwaitinterval() {
		int mqcih__getwaitinterval = 0;
		mqcih__getwaitinterval = MarshallIntegerUtils.unmarshallFourByteIntegerFromBuffer(buffer_, 48, true, MarshallIntegerUtils.SIGN_CODING_TWOS_COMPLEMENT);
		return (mqcih__getwaitinterval);
	}

	/**
	 * @generated
	 */
	public void setMqcih__getwaitinterval(int mqcih__getwaitinterval) {
		if ((mqcih__getwaitinterval < -999999999) || (mqcih__getwaitinterval > 999999999))
			throw new IllegalArgumentException(MarshallResource.instance().getString(MarshallResource.IWAA0127E, Integer.toString(mqcih__getwaitinterval), "mqcih__getwaitinterval", "-999999999", "999999999"));
		MarshallIntegerUtils.marshallFourByteIntegerIntoBuffer(mqcih__getwaitinterval, buffer_, 48, true, MarshallIntegerUtils.SIGN_CODING_TWOS_COMPLEMENT);
	}

	/**
	 * @generated
	 * @type-descriptor.restriction lowerBound="-999999999" upperBound="999999999"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="4" offset="52" size="4"
	 * @type-descriptor.integer-td signCoding="twosComplement"
	 */
	public int getMqcih__linktype() {
		int mqcih__linktype = 0;
		mqcih__linktype = MarshallIntegerUtils.unmarshallFourByteIntegerFromBuffer(buffer_, 52, true, MarshallIntegerUtils.SIGN_CODING_TWOS_COMPLEMENT);
		return (mqcih__linktype);
	}

	/**
	 * @generated
	 */
	public void setMqcih__linktype(int mqcih__linktype) {
		if ((mqcih__linktype < -999999999) || (mqcih__linktype > 999999999))
			throw new IllegalArgumentException(MarshallResource.instance().getString(MarshallResource.IWAA0127E, Integer.toString(mqcih__linktype), "mqcih__linktype", "-999999999", "999999999"));
		MarshallIntegerUtils.marshallFourByteIntegerIntoBuffer(mqcih__linktype, buffer_, 52, true, MarshallIntegerUtils.SIGN_CODING_TWOS_COMPLEMENT);
	}

	/**
	 * @generated
	 * @type-descriptor.restriction lowerBound="-999999999" upperBound="999999999"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="4" offset="56" size="4"
	 * @type-descriptor.integer-td signCoding="twosComplement"
	 */
	public int getMqcih__outputdatalength() {
		int mqcih__outputdatalength = 0;
		mqcih__outputdatalength = MarshallIntegerUtils.unmarshallFourByteIntegerFromBuffer(buffer_, 56, true, MarshallIntegerUtils.SIGN_CODING_TWOS_COMPLEMENT);
		return (mqcih__outputdatalength);
	}

	/**
	 * @generated
	 */
	public void setMqcih__outputdatalength(int mqcih__outputdatalength) {
		if ((mqcih__outputdatalength < -999999999) || (mqcih__outputdatalength > 999999999))
			throw new IllegalArgumentException(MarshallResource.instance().getString(MarshallResource.IWAA0127E, Integer.toString(mqcih__outputdatalength), "mqcih__outputdatalength", "-999999999", "999999999"));
		MarshallIntegerUtils.marshallFourByteIntegerIntoBuffer(mqcih__outputdatalength, buffer_, 56, true, MarshallIntegerUtils.SIGN_CODING_TWOS_COMPLEMENT);
	}

	/**
	 * @generated
	 * @type-descriptor.restriction lowerBound="-999999999" upperBound="999999999"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="4" offset="60" size="4"
	 * @type-descriptor.integer-td signCoding="twosComplement"
	 */
	public int getMqcih__facilitykeeptime() {
		int mqcih__facilitykeeptime = 0;
		mqcih__facilitykeeptime = MarshallIntegerUtils.unmarshallFourByteIntegerFromBuffer(buffer_, 60, true, MarshallIntegerUtils.SIGN_CODING_TWOS_COMPLEMENT);
		return (mqcih__facilitykeeptime);
	}

	/**
	 * @generated
	 */
	public void setMqcih__facilitykeeptime(int mqcih__facilitykeeptime) {
		if ((mqcih__facilitykeeptime < -999999999) || (mqcih__facilitykeeptime > 999999999))
			throw new IllegalArgumentException(MarshallResource.instance().getString(MarshallResource.IWAA0127E, Integer.toString(mqcih__facilitykeeptime), "mqcih__facilitykeeptime", "-999999999", "999999999"));
		MarshallIntegerUtils.marshallFourByteIntegerIntoBuffer(mqcih__facilitykeeptime, buffer_, 60, true, MarshallIntegerUtils.SIGN_CODING_TWOS_COMPLEMENT);
	}

	/**
	 * @generated
	 * @type-descriptor.restriction lowerBound="-999999999" upperBound="999999999"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="4" offset="64" size="4"
	 * @type-descriptor.integer-td signCoding="twosComplement"
	 */
	public int getMqcih__adsdescriptor() {
		int mqcih__adsdescriptor = 0;
		mqcih__adsdescriptor = MarshallIntegerUtils.unmarshallFourByteIntegerFromBuffer(buffer_, 64, true, MarshallIntegerUtils.SIGN_CODING_TWOS_COMPLEMENT);
		return (mqcih__adsdescriptor);
	}

	/**
	 * @generated
	 */
	public void setMqcih__adsdescriptor(int mqcih__adsdescriptor) {
		if ((mqcih__adsdescriptor < -999999999) || (mqcih__adsdescriptor > 999999999))
			throw new IllegalArgumentException(MarshallResource.instance().getString(MarshallResource.IWAA0127E, Integer.toString(mqcih__adsdescriptor), "mqcih__adsdescriptor", "-999999999", "999999999"));
		MarshallIntegerUtils.marshallFourByteIntegerIntoBuffer(mqcih__adsdescriptor, buffer_, 64, true, MarshallIntegerUtils.SIGN_CODING_TWOS_COMPLEMENT);
	}

	/**
	 * @generated
	 * @type-descriptor.restriction lowerBound="-999999999" upperBound="999999999"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="4" offset="68" size="4"
	 * @type-descriptor.integer-td signCoding="twosComplement"
	 */
	public int getMqcih__conversationaltask() {
		int mqcih__conversationaltask = 0;
		mqcih__conversationaltask = MarshallIntegerUtils.unmarshallFourByteIntegerFromBuffer(buffer_, 68, true, MarshallIntegerUtils.SIGN_CODING_TWOS_COMPLEMENT);
		return (mqcih__conversationaltask);
	}

	/**
	 * @generated
	 */
	public void setMqcih__conversationaltask(int mqcih__conversationaltask) {
		if ((mqcih__conversationaltask < -999999999) || (mqcih__conversationaltask > 999999999))
			throw new IllegalArgumentException(MarshallResource.instance().getString(MarshallResource.IWAA0127E, Integer.toString(mqcih__conversationaltask), "mqcih__conversationaltask", "-999999999", "999999999"));
		MarshallIntegerUtils.marshallFourByteIntegerIntoBuffer(mqcih__conversationaltask, buffer_, 68, true, MarshallIntegerUtils.SIGN_CODING_TWOS_COMPLEMENT);
	}

	/**
	 * @generated
	 * @type-descriptor.restriction lowerBound="-999999999" upperBound="999999999"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="4" offset="72" size="4"
	 * @type-descriptor.integer-td signCoding="twosComplement"
	 */
	public int getMqcih__taskendstatus() {
		int mqcih__taskendstatus = 0;
		mqcih__taskendstatus = MarshallIntegerUtils.unmarshallFourByteIntegerFromBuffer(buffer_, 72, true, MarshallIntegerUtils.SIGN_CODING_TWOS_COMPLEMENT);
		return (mqcih__taskendstatus);
	}

	/**
	 * @generated
	 */
	public void setMqcih__taskendstatus(int mqcih__taskendstatus) {
		if ((mqcih__taskendstatus < -999999999) || (mqcih__taskendstatus > 999999999))
			throw new IllegalArgumentException(MarshallResource.instance().getString(MarshallResource.IWAA0127E, Integer.toString(mqcih__taskendstatus), "mqcih__taskendstatus", "-999999999", "999999999"));
		MarshallIntegerUtils.marshallFourByteIntegerIntoBuffer(mqcih__taskendstatus, buffer_, 72, true, MarshallIntegerUtils.SIGN_CODING_TWOS_COMPLEMENT);
	}

	/**
	 * @generated
	 * @type-descriptor.restriction maxLength="8"
	 * @type-descriptor.initial-value kind="SPACE"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="8" offset="76" size="8"
	 * @type-descriptor.string-td characterSize="1" lengthEncoding="fixedLength" paddingCharacter=" " prefixLength="0"
	 */
	public String getMqcih__facility() {
		String mqcih__facility = null;
		mqcih__facility = MarshallStringUtils.unmarshallFixedLengthStringFromBuffer(buffer_, 76, "IBM-037", 8);
		return (mqcih__facility);
	}

	/**
	 * @generated
	 */
	public void setMqcih__facility(String mqcih__facility) {
		if (mqcih__facility != null) {
			if (mqcih__facility.length() > 8)
				throw new IllegalArgumentException(MarshallResource.instance().getString(MarshallResource.IWAA0124E, mqcih__facility, "8", "mqcih__facility"));
			MarshallStringUtils.marshallFixedLengthStringIntoBuffer(mqcih__facility, buffer_, 76, "IBM-037", 8, MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
		}
	}

	/**
	 * @generated
	 * @type-descriptor.restriction maxLength="4"
	 * @type-descriptor.initial-value kind="SPACE"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="4" offset="84" size="4"
	 * @type-descriptor.string-td characterSize="1" lengthEncoding="fixedLength" paddingCharacter=" " prefixLength="0"
	 */
	public String getMqcih__function() {
		String mqcih__function = null;
		mqcih__function = MarshallStringUtils.unmarshallFixedLengthStringFromBuffer(buffer_, 84, "IBM-037", 4);
		return (mqcih__function);
	}

	/**
	 * @generated
	 */
	public void setMqcih__function(String mqcih__function) {
		if (mqcih__function != null) {
			if (mqcih__function.length() > 4)
				throw new IllegalArgumentException(MarshallResource.instance().getString(MarshallResource.IWAA0124E, mqcih__function, "4", "mqcih__function"));
			MarshallStringUtils.marshallFixedLengthStringIntoBuffer(mqcih__function, buffer_, 84, "IBM-037", 4, MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
		}
	}

	/**
	 * @generated
	 * @type-descriptor.restriction maxLength="4"
	 * @type-descriptor.initial-value kind="SPACE"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="4" offset="88" size="4"
	 * @type-descriptor.string-td characterSize="1" lengthEncoding="fixedLength" paddingCharacter=" " prefixLength="0"
	 */
	public String getMqcih__abendcode() {
		String mqcih__abendcode = null;
		mqcih__abendcode = MarshallStringUtils.unmarshallFixedLengthStringFromBuffer(buffer_, 88, "IBM-037", 4);
		return (mqcih__abendcode);
	}

	/**
	 * @generated
	 */
	public void setMqcih__abendcode(String mqcih__abendcode) {
		if (mqcih__abendcode != null) {
			if (mqcih__abendcode.length() > 4)
				throw new IllegalArgumentException(MarshallResource.instance().getString(MarshallResource.IWAA0124E, mqcih__abendcode, "4", "mqcih__abendcode"));
			MarshallStringUtils.marshallFixedLengthStringIntoBuffer(mqcih__abendcode, buffer_, 88, "IBM-037", 4, MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
		}
	}

	/**
	 * @generated
	 * @type-descriptor.restriction maxLength="8"
	 * @type-descriptor.initial-value kind="SPACE"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="8" offset="92" size="8"
	 * @type-descriptor.string-td characterSize="1" lengthEncoding="fixedLength" paddingCharacter=" " prefixLength="0"
	 */
	public String getMqcih__authenticator() {
		String mqcih__authenticator = null;
		mqcih__authenticator = MarshallStringUtils.unmarshallFixedLengthStringFromBuffer(buffer_, 92, "IBM-037", 8);
		return (mqcih__authenticator);
	}

	/**
	 * @generated
	 */
	public void setMqcih__authenticator(String mqcih__authenticator) {
		if (mqcih__authenticator != null) {
			if (mqcih__authenticator.length() > 8)
				throw new IllegalArgumentException(MarshallResource.instance().getString(MarshallResource.IWAA0124E, mqcih__authenticator, "8", "mqcih__authenticator"));
			MarshallStringUtils.marshallFixedLengthStringIntoBuffer(mqcih__authenticator, buffer_, 92, "IBM-037", 8, MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
		}
	}

	/**
	 * @generated
	 * @type-descriptor.restriction maxLength="8"
	 * @type-descriptor.initial-value kind="SPACE"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="8" offset="100" size="8"
	 * @type-descriptor.string-td characterSize="1" lengthEncoding="fixedLength" paddingCharacter=" " prefixLength="0"
	 */
	public String getMqcih__reserved1() {
		String mqcih__reserved1 = null;
		mqcih__reserved1 = MarshallStringUtils.unmarshallFixedLengthStringFromBuffer(buffer_, 100, "IBM-037", 8);
		return (mqcih__reserved1);
	}

	/**
	 * @generated
	 */
	public void setMqcih__reserved1(String mqcih__reserved1) {
		if (mqcih__reserved1 != null) {
			if (mqcih__reserved1.length() > 8)
				throw new IllegalArgumentException(MarshallResource.instance().getString(MarshallResource.IWAA0124E, mqcih__reserved1, "8", "mqcih__reserved1"));
			MarshallStringUtils.marshallFixedLengthStringIntoBuffer(mqcih__reserved1, buffer_, 100, "IBM-037", 8, MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
		}
	}

	/**
	 * @generated
	 * @type-descriptor.restriction maxLength="8"
	 * @type-descriptor.initial-value kind="SPACE"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="8" offset="108" size="8"
	 * @type-descriptor.string-td characterSize="1" lengthEncoding="fixedLength" paddingCharacter=" " prefixLength="0"
	 */
	public String getMqcih__replytoformat() {
		String mqcih__replytoformat = null;
		mqcih__replytoformat = MarshallStringUtils.unmarshallFixedLengthStringFromBuffer(buffer_, 108, "IBM-037", 8);
		return (mqcih__replytoformat);
	}

	/**
	 * @generated
	 */
	public void setMqcih__replytoformat(String mqcih__replytoformat) {
		if (mqcih__replytoformat != null) {
			if (mqcih__replytoformat.length() > 8)
				throw new IllegalArgumentException(MarshallResource.instance().getString(MarshallResource.IWAA0124E, mqcih__replytoformat, "8", "mqcih__replytoformat"));
			MarshallStringUtils.marshallFixedLengthStringIntoBuffer(mqcih__replytoformat, buffer_, 108, "IBM-037", 8, MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
		}
	}

	/**
	 * @generated
	 * @type-descriptor.restriction maxLength="4"
	 * @type-descriptor.initial-value kind="SPACE"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="4" offset="116" size="4"
	 * @type-descriptor.string-td characterSize="1" lengthEncoding="fixedLength" paddingCharacter=" " prefixLength="0"
	 */
	public String getMqcih__remotesysid() {
		String mqcih__remotesysid = null;
		mqcih__remotesysid = MarshallStringUtils.unmarshallFixedLengthStringFromBuffer(buffer_, 116, "IBM-037", 4);
		return (mqcih__remotesysid);
	}

	/**
	 * @generated
	 */
	public void setMqcih__remotesysid(String mqcih__remotesysid) {
		if (mqcih__remotesysid != null) {
			if (mqcih__remotesysid.length() > 4)
				throw new IllegalArgumentException(MarshallResource.instance().getString(MarshallResource.IWAA0124E, mqcih__remotesysid, "4", "mqcih__remotesysid"));
			MarshallStringUtils.marshallFixedLengthStringIntoBuffer(mqcih__remotesysid, buffer_, 116, "IBM-037", 4, MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
		}
	}

	/**
	 * @generated
	 * @type-descriptor.restriction maxLength="4"
	 * @type-descriptor.initial-value kind="SPACE"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="4" offset="120" size="4"
	 * @type-descriptor.string-td characterSize="1" lengthEncoding="fixedLength" paddingCharacter=" " prefixLength="0"
	 */
	public String getMqcih__remotetransid() {
		String mqcih__remotetransid = null;
		mqcih__remotetransid = MarshallStringUtils.unmarshallFixedLengthStringFromBuffer(buffer_, 120, "IBM-037", 4);
		return (mqcih__remotetransid);
	}

	/**
	 * @generated
	 */
	public void setMqcih__remotetransid(String mqcih__remotetransid) {
		if (mqcih__remotetransid != null) {
			if (mqcih__remotetransid.length() > 4)
				throw new IllegalArgumentException(MarshallResource.instance().getString(MarshallResource.IWAA0124E, mqcih__remotetransid, "4", "mqcih__remotetransid"));
			MarshallStringUtils.marshallFixedLengthStringIntoBuffer(mqcih__remotetransid, buffer_, 120, "IBM-037", 4, MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
		}
	}

	/**
	 * @generated
	 * @type-descriptor.restriction maxLength="4"
	 * @type-descriptor.initial-value kind="SPACE"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="4" offset="124" size="4"
	 * @type-descriptor.string-td characterSize="1" lengthEncoding="fixedLength" paddingCharacter=" " prefixLength="0"
	 */
	public String getMqcih__transactionid() {
		String mqcih__transactionid = null;
		mqcih__transactionid = MarshallStringUtils.unmarshallFixedLengthStringFromBuffer(buffer_, 124, "IBM-037", 4);
		return (mqcih__transactionid);
	}

	/**
	 * @generated
	 */
	public void setMqcih__transactionid(String mqcih__transactionid) {
		if (mqcih__transactionid != null) {
			if (mqcih__transactionid.length() > 4)
				throw new IllegalArgumentException(MarshallResource.instance().getString(MarshallResource.IWAA0124E, mqcih__transactionid, "4", "mqcih__transactionid"));
			MarshallStringUtils.marshallFixedLengthStringIntoBuffer(mqcih__transactionid, buffer_, 124, "IBM-037", 4, MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
		}
	}

	/**
	 * @generated
	 * @type-descriptor.restriction maxLength="4"
	 * @type-descriptor.initial-value kind="SPACE"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="4" offset="128" size="4"
	 * @type-descriptor.string-td characterSize="1" lengthEncoding="fixedLength" paddingCharacter=" " prefixLength="0"
	 */
	public String getMqcih__facilitylike() {
		String mqcih__facilitylike = null;
		mqcih__facilitylike = MarshallStringUtils.unmarshallFixedLengthStringFromBuffer(buffer_, 128, "IBM-037", 4);
		return (mqcih__facilitylike);
	}

	/**
	 * @generated
	 */
	public void setMqcih__facilitylike(String mqcih__facilitylike) {
		if (mqcih__facilitylike != null) {
			if (mqcih__facilitylike.length() > 4)
				throw new IllegalArgumentException(MarshallResource.instance().getString(MarshallResource.IWAA0124E, mqcih__facilitylike, "4", "mqcih__facilitylike"));
			MarshallStringUtils.marshallFixedLengthStringIntoBuffer(mqcih__facilitylike, buffer_, 128, "IBM-037", 4, MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
		}
	}

	/**
	 * @generated
	 * @type-descriptor.restriction maxLength="4"
	 * @type-descriptor.initial-value kind="SPACE"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="4" offset="132" size="4"
	 * @type-descriptor.string-td characterSize="1" lengthEncoding="fixedLength" paddingCharacter=" " prefixLength="0"
	 */
	public String getMqcih__attentionid() {
		String mqcih__attentionid = null;
		mqcih__attentionid = MarshallStringUtils.unmarshallFixedLengthStringFromBuffer(buffer_, 132, "IBM-037", 4);
		return (mqcih__attentionid);
	}

	/**
	 * @generated
	 */
	public void setMqcih__attentionid(String mqcih__attentionid) {
		if (mqcih__attentionid != null) {
			if (mqcih__attentionid.length() > 4)
				throw new IllegalArgumentException(MarshallResource.instance().getString(MarshallResource.IWAA0124E, mqcih__attentionid, "4", "mqcih__attentionid"));
			MarshallStringUtils.marshallFixedLengthStringIntoBuffer(mqcih__attentionid, buffer_, 132, "IBM-037", 4, MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
		}
	}

	/**
	 * @generated
	 * @type-descriptor.restriction maxLength="4"
	 * @type-descriptor.initial-value kind="SPACE"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="4" offset="136" size="4"
	 * @type-descriptor.string-td characterSize="1" lengthEncoding="fixedLength" paddingCharacter=" " prefixLength="0"
	 */
	public String getMqcih__startcode() {
		String mqcih__startcode = null;
		mqcih__startcode = MarshallStringUtils.unmarshallFixedLengthStringFromBuffer(buffer_, 136, "IBM-037", 4);
		return (mqcih__startcode);
	}

	/**
	 * @generated
	 */
	public void setMqcih__startcode(String mqcih__startcode) {
		if (mqcih__startcode != null) {
			if (mqcih__startcode.length() > 4)
				throw new IllegalArgumentException(MarshallResource.instance().getString(MarshallResource.IWAA0124E, mqcih__startcode, "4", "mqcih__startcode"));
			MarshallStringUtils.marshallFixedLengthStringIntoBuffer(mqcih__startcode, buffer_, 136, "IBM-037", 4, MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
		}
	}

	/**
	 * @generated
	 * @type-descriptor.restriction maxLength="4"
	 * @type-descriptor.initial-value kind="SPACE"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="4" offset="140" size="4"
	 * @type-descriptor.string-td characterSize="1" lengthEncoding="fixedLength" paddingCharacter=" " prefixLength="0"
	 */
	public String getMqcih__cancelcode() {
		String mqcih__cancelcode = null;
		mqcih__cancelcode = MarshallStringUtils.unmarshallFixedLengthStringFromBuffer(buffer_, 140, "IBM-037", 4);
		return (mqcih__cancelcode);
	}

	/**
	 * @generated
	 */
	public void setMqcih__cancelcode(String mqcih__cancelcode) {
		if (mqcih__cancelcode != null) {
			if (mqcih__cancelcode.length() > 4)
				throw new IllegalArgumentException(MarshallResource.instance().getString(MarshallResource.IWAA0124E, mqcih__cancelcode, "4", "mqcih__cancelcode"));
			MarshallStringUtils.marshallFixedLengthStringIntoBuffer(mqcih__cancelcode, buffer_, 140, "IBM-037", 4, MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
		}
	}

	/**
	 * @generated
	 * @type-descriptor.restriction maxLength="4"
	 * @type-descriptor.initial-value kind="SPACE"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="4" offset="144" size="4"
	 * @type-descriptor.string-td characterSize="1" lengthEncoding="fixedLength" paddingCharacter=" " prefixLength="0"
	 */
	public String getMqcih__nexttransactionid() {
		String mqcih__nexttransactionid = null;
		mqcih__nexttransactionid = MarshallStringUtils.unmarshallFixedLengthStringFromBuffer(buffer_, 144, "IBM-037", 4);
		return (mqcih__nexttransactionid);
	}

	/**
	 * @generated
	 */
	public void setMqcih__nexttransactionid(String mqcih__nexttransactionid) {
		if (mqcih__nexttransactionid != null) {
			if (mqcih__nexttransactionid.length() > 4)
				throw new IllegalArgumentException(MarshallResource.instance().getString(MarshallResource.IWAA0124E, mqcih__nexttransactionid, "4", "mqcih__nexttransactionid"));
			MarshallStringUtils.marshallFixedLengthStringIntoBuffer(mqcih__nexttransactionid, buffer_, 144, "IBM-037", 4, MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
		}
	}

	/**
	 * @generated
	 * @type-descriptor.restriction maxLength="8"
	 * @type-descriptor.initial-value kind="SPACE"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="8" offset="148" size="8"
	 * @type-descriptor.string-td characterSize="1" lengthEncoding="fixedLength" paddingCharacter=" " prefixLength="0"
	 */
	public String getMqcih__reserved2() {
		String mqcih__reserved2 = null;
		mqcih__reserved2 = MarshallStringUtils.unmarshallFixedLengthStringFromBuffer(buffer_, 148, "IBM-037", 8);
		return (mqcih__reserved2);
	}

	/**
	 * @generated
	 */
	public void setMqcih__reserved2(String mqcih__reserved2) {
		if (mqcih__reserved2 != null) {
			if (mqcih__reserved2.length() > 8)
				throw new IllegalArgumentException(MarshallResource.instance().getString(MarshallResource.IWAA0124E, mqcih__reserved2, "8", "mqcih__reserved2"));
			MarshallStringUtils.marshallFixedLengthStringIntoBuffer(mqcih__reserved2, buffer_, 148, "IBM-037", 8, MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
		}
	}

	/**
	 * @generated
	 * @type-descriptor.restriction maxLength="8"
	 * @type-descriptor.initial-value kind="SPACE"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="8" offset="156" size="8"
	 * @type-descriptor.string-td characterSize="1" lengthEncoding="fixedLength" paddingCharacter=" " prefixLength="0"
	 */
	public String getMqcih__reserved3() {
		String mqcih__reserved3 = null;
		mqcih__reserved3 = MarshallStringUtils.unmarshallFixedLengthStringFromBuffer(buffer_, 156, "IBM-037", 8);
		return (mqcih__reserved3);
	}

	/**
	 * @generated
	 */
	public void setMqcih__reserved3(String mqcih__reserved3) {
		if (mqcih__reserved3 != null) {
			if (mqcih__reserved3.length() > 8)
				throw new IllegalArgumentException(MarshallResource.instance().getString(MarshallResource.IWAA0124E, mqcih__reserved3, "8", "mqcih__reserved3"));
			MarshallStringUtils.marshallFixedLengthStringIntoBuffer(mqcih__reserved3, buffer_, 156, "IBM-037", 8, MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
		}
	}

	/**
	 * @generated
	 * @type-descriptor.restriction lowerBound="-999999999" upperBound="999999999"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="4" offset="164" size="4"
	 * @type-descriptor.integer-td signCoding="twosComplement"
	 */
	public int getMqcih__cursorposition() {
		int mqcih__cursorposition = 0;
		mqcih__cursorposition = MarshallIntegerUtils.unmarshallFourByteIntegerFromBuffer(buffer_, 164, true, MarshallIntegerUtils.SIGN_CODING_TWOS_COMPLEMENT);
		return (mqcih__cursorposition);
	}

	/**
	 * @generated
	 */
	public void setMqcih__cursorposition(int mqcih__cursorposition) {
		if ((mqcih__cursorposition < -999999999) || (mqcih__cursorposition > 999999999))
			throw new IllegalArgumentException(MarshallResource.instance().getString(MarshallResource.IWAA0127E, Integer.toString(mqcih__cursorposition), "mqcih__cursorposition", "-999999999", "999999999"));
		MarshallIntegerUtils.marshallFourByteIntegerIntoBuffer(mqcih__cursorposition, buffer_, 164, true, MarshallIntegerUtils.SIGN_CODING_TWOS_COMPLEMENT);
	}

	/**
	 * @generated
	 * @type-descriptor.restriction lowerBound="-999999999" upperBound="999999999"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="4" offset="168" size="4"
	 * @type-descriptor.integer-td signCoding="twosComplement"
	 */
	public int getMqcih__erroroffset() {
		int mqcih__erroroffset = 0;
		mqcih__erroroffset = MarshallIntegerUtils.unmarshallFourByteIntegerFromBuffer(buffer_, 168, true, MarshallIntegerUtils.SIGN_CODING_TWOS_COMPLEMENT);
		return (mqcih__erroroffset);
	}

	/**
	 * @generated
	 */
	public void setMqcih__erroroffset(int mqcih__erroroffset) {
		if ((mqcih__erroroffset < -999999999) || (mqcih__erroroffset > 999999999))
			throw new IllegalArgumentException(MarshallResource.instance().getString(MarshallResource.IWAA0127E, Integer.toString(mqcih__erroroffset), "mqcih__erroroffset", "-999999999", "999999999"));
		MarshallIntegerUtils.marshallFourByteIntegerIntoBuffer(mqcih__erroroffset, buffer_, 168, true, MarshallIntegerUtils.SIGN_CODING_TWOS_COMPLEMENT);
	}

	/**
	 * @generated
	 * @type-descriptor.restriction lowerBound="-999999999" upperBound="999999999"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="4" offset="172" size="4"
	 * @type-descriptor.integer-td signCoding="twosComplement"
	 */
	public int getMqcih__inputitem() {
		int mqcih__inputitem = 0;
		mqcih__inputitem = MarshallIntegerUtils.unmarshallFourByteIntegerFromBuffer(buffer_, 172, true, MarshallIntegerUtils.SIGN_CODING_TWOS_COMPLEMENT);
		return (mqcih__inputitem);
	}

	/**
	 * @generated
	 */
	public void setMqcih__inputitem(int mqcih__inputitem) {
		if ((mqcih__inputitem < -999999999) || (mqcih__inputitem > 999999999))
			throw new IllegalArgumentException(MarshallResource.instance().getString(MarshallResource.IWAA0127E, Integer.toString(mqcih__inputitem), "mqcih__inputitem", "-999999999", "999999999"));
		MarshallIntegerUtils.marshallFourByteIntegerIntoBuffer(mqcih__inputitem, buffer_, 172, true, MarshallIntegerUtils.SIGN_CODING_TWOS_COMPLEMENT);
	}

	/**
	 * @generated
	 * @type-descriptor.restriction lowerBound="-999999999" upperBound="999999999"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="4" offset="176" size="4"
	 * @type-descriptor.integer-td signCoding="twosComplement"
	 */
	public int getMqcih__reserved4() {
		int mqcih__reserved4 = 0;
		mqcih__reserved4 = MarshallIntegerUtils.unmarshallFourByteIntegerFromBuffer(buffer_, 176, true, MarshallIntegerUtils.SIGN_CODING_TWOS_COMPLEMENT);
		return (mqcih__reserved4);
	}

	/**
	 * @generated
	 */
	public void setMqcih__reserved4(int mqcih__reserved4) {
		if ((mqcih__reserved4 < -999999999) || (mqcih__reserved4 > 999999999))
			throw new IllegalArgumentException(MarshallResource.instance().getString(MarshallResource.IWAA0127E, Integer.toString(mqcih__reserved4), "mqcih__reserved4", "-999999999", "999999999"));
		MarshallIntegerUtils.marshallFourByteIntegerIntoBuffer(mqcih__reserved4, buffer_, 176, true, MarshallIntegerUtils.SIGN_CODING_TWOS_COMPLEMENT);
	}

}