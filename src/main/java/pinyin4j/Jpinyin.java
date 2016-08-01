package pinyin4j;

import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;

public class Jpinyin {
	public static void main(String[] args) {
		String str = "你好世界";
		String ss1 = PinyinHelper.convertToPinyinString(str, ",",
				PinyinFormat.WITH_TONE_MARK); // nǐ,hǎo,shì,jiè
		String ss2 = PinyinHelper.convertToPinyinString(str, ",",
				PinyinFormat.WITH_TONE_NUMBER); // ni3,hao3,shi4,jie4
		String ss3 = PinyinHelper.convertToPinyinString(str, "",
				PinyinFormat.WITHOUT_TONE); // ni,hao,shi,jie
		String ss4 = PinyinHelper.getShortPinyin(str); // nhsj
		System.out.println(ss1);
		System.out.println(ss2);
		System.out.println(ss3);
		System.out.println(ss4);
	}
}
