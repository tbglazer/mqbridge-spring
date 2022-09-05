package il.co.fibi.comm.mqbridge.headers;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import com.ibm.etools.marshall.util.*;

/**
 * @generated
 * Generated Class: BRMQVECTORHEADER
 * @type-descriptor.aggregate-instance-td accessor="readWrite" contentSize="16" offset="0" size="16"
 * @type-descriptor.platform-compiler-info language="COBOL" defaultBigEndian="true" defaultCodepage="IBM-037" defaultExternalDecimalSign="ebcdic" defaultFloatType="ibm390Hex"
 */
@SuppressWarnings({"rawtypes","unused","unchecked"})
public class BRMQVECTORHEADER implements javax.resource.cci.Record, javax.resource.cci.Streamable, com.ibm.etools.marshall.RecordBytes {
	private static final long serialVersionUID = -1339338968999751352L;
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
		bufferSize_ = 16;
		initializedBuffer_ = new byte[bufferSize_];
		String brmq__vector__typeInitialValue = " ";
		MarshallStringUtils.marshallFixedLengthStringIntoBuffer(brmq__vector__typeInitialValue, initializedBuffer_, 8, "IBM-037", 4, MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
		String brmq__vector__versionInitialValue = " ";
		MarshallStringUtils.marshallFixedLengthStringIntoBuffer(brmq__vector__versionInitialValue, initializedBuffer_, 12, "IBM-037", 4, MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
		String brmq__vector__descriptorInitialValue = " ";
		MarshallStringUtils.marshallFixedLengthStringIntoBuffer(brmq__vector__descriptorInitialValue, initializedBuffer_, 4, "IBM-037", 4, MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
	}

	/**
	 * constructor
	 * @generated
	 */
	public BRMQVECTORHEADER() {
		initialize();
	}

	/**
	 * constructor
	 * @generated
	 */
	public BRMQVECTORHEADER(java.util.HashMap valFieldNameMap) {
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
		return (16);
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
				fieldValue = 0;
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
	 * @type-descriptor.restriction lowerBound="-99999999" upperBound="99999999"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="4" offset="0" size="4"
	 * @type-descriptor.integer-td signCoding="twosComplement"
	 */
	public int getBrmq__vector__length() {
		int brmq__vector__length = 0;
		brmq__vector__length = MarshallIntegerUtils.unmarshallFourByteIntegerFromBuffer(buffer_, 0, true, MarshallIntegerUtils.SIGN_CODING_TWOS_COMPLEMENT);
		return (brmq__vector__length);
	}

	/**
	 * @generated
	 */
	public void setBrmq__vector__length(int brmq__vector__length) {
		if ((brmq__vector__length < -99999999) || (brmq__vector__length > 99999999))
			throw new IllegalArgumentException(MarshallResource.instance().getString(MarshallResource.IWAA0127E, Integer.toString(brmq__vector__length), "brmq__vector__length", "-99999999", "99999999"));
		MarshallIntegerUtils.marshallFourByteIntegerIntoBuffer(brmq__vector__length, buffer_, 0, true, MarshallIntegerUtils.SIGN_CODING_TWOS_COMPLEMENT);
	}

	/**
	 * @generated
	 * @type-descriptor.restriction maxLength="4"
	 * @type-descriptor.initial-value kind="SPACE"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="4" offset="4" size="4"
	 * @type-descriptor.string-td characterSize="1" lengthEncoding="fixedLength" paddingCharacter=" " prefixLength="0"
	 */
	public String getBrmq__vector__descriptor() {
		String brmq__vector__descriptor = null;
		brmq__vector__descriptor = MarshallStringUtils.unmarshallFixedLengthStringFromBuffer(buffer_, 4, "IBM-037", 4);
		return (brmq__vector__descriptor);
	}

	/**
	 * @generated
	 */
	public void setBrmq__vector__descriptor(String brmq__vector__descriptor) {
		if (brmq__vector__descriptor != null) {
			if (brmq__vector__descriptor.length() > 4)
				throw new IllegalArgumentException(MarshallResource.instance().getString(MarshallResource.IWAA0124E, brmq__vector__descriptor, "4", "brmq__vector__descriptor"));
			MarshallStringUtils.marshallFixedLengthStringIntoBuffer(brmq__vector__descriptor, buffer_, 4, "IBM-037", 4, MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
		}
	}

	/**
	 * @generated
	 * @type-descriptor.restriction maxLength="4"
	 * @type-descriptor.initial-value kind="SPACE"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="4" offset="8" size="4"
	 * @type-descriptor.string-td characterSize="1" lengthEncoding="fixedLength" paddingCharacter=" " prefixLength="0"
	 */
	public String getBrmq__vector__type() {
		String brmq__vector__type = null;
		brmq__vector__type = MarshallStringUtils.unmarshallFixedLengthStringFromBuffer(buffer_, 8, "IBM-037", 4);
		return (brmq__vector__type);
	}

	/**
	 * @generated
	 */
	public void setBrmq__vector__type(String brmq__vector__type) {
		if (brmq__vector__type != null) {
			if (brmq__vector__type.length() > 4)
				throw new IllegalArgumentException(MarshallResource.instance().getString(MarshallResource.IWAA0124E, brmq__vector__type, "4", "brmq__vector__type"));
			MarshallStringUtils.marshallFixedLengthStringIntoBuffer(brmq__vector__type, buffer_, 8, "IBM-037", 4, MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
		}
	}

	/**
	 * @generated
	 * @type-descriptor.restriction maxLength="4"
	 * @type-descriptor.initial-value kind="SPACE"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="4" offset="12" size="4"
	 * @type-descriptor.string-td characterSize="1" lengthEncoding="fixedLength" paddingCharacter=" " prefixLength="0"
	 */
	public String getBrmq__vector__version() {
		String brmq__vector__version = null;
		brmq__vector__version = MarshallStringUtils.unmarshallFixedLengthStringFromBuffer(buffer_, 12, "IBM-037", 4);
		return (brmq__vector__version);
	}

	/**
	 * @generated
	 */
	public void setBrmq__vector__version(String brmq__vector__version) {
		if (brmq__vector__version != null) {
			if (brmq__vector__version.length() > 4)
				throw new IllegalArgumentException(MarshallResource.instance().getString(MarshallResource.IWAA0124E, brmq__vector__version, "4", "brmq__vector__version"));
			MarshallStringUtils.marshallFixedLengthStringIntoBuffer(brmq__vector__version, buffer_, 12, "IBM-037", 4, MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
		}
	}

}