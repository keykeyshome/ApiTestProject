package api;

import com.github.crab2died.converter.WriteConvertible;

public class BooleanWriteConvert implements WriteConvertible{

	@Override
	public Object execWrite(Object object) {
		boolean b = (Boolean)object;
		return b==true?"是":"未开启";
	}

}
