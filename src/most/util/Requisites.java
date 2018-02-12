package most.util;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import most.model.Form;
import most.model.RecMiss;
import most.model.Setting;

public class Requisites {
	private Map<String, Form> mapForms;

	private Requisites() {}

	public Requisites(Map<String, Form> mapForms) {
		this.mapForms = mapForms;
	}

	private static final List<String> REC_NEED_CONVERT = Arrays.asList("Р[1-13-1]", "Р[1-13-2]", "Р[1-13.1-1]", "Р[1-13.1-2]", "Р[1-13.1-3]",
			"Р[1-30.1]", "Р[1-30.2]","Р[1-30.3]","Р[1-30.4]","Р[1-30.5]", "Р[1-30.6]", "Р[1-30.7]", "Р[1-30.8]","Р[1-30.9]","Р[1-30.10]",
			"Р[1-30.11]", "Р[1-30.12]", "Р[1-5-1]", "Р[1-5-2]", "Р[3-2]",
			"Р[2-21.1-1]", "Р[2-21.1-2]", "Р[2-21.2-1]", "Р[2-21.2-2]", "Р[2-21.3-1]", "Р[2-21.3-2]", "Р[2-21.4-1]", "Р[2-21.4-2]",
			"Р[2-21.5-1]", "Р[2-21.5-2]", "Р[2-21.6-1]", "Р[2-21.6-2]", "Р[4-6-1]", "Р[4-6-2]",
			"Р[5-7.1-1]", "Р[5-7.1-2]", "Р[5-7.2-1]", "Р[5-7.2-2]", "Р[5-7.3-1]", "Р[5-7.3-2]","Р[6-15.1-3]", "Р[6-15.1-4]", "Р[6-15.2-3]",
			"Р[6-15.2-4]", "Р[6-15.3-3]", "Р[6-15.3-4]", "Р[6-15.4-3]", "Р[6-15.4-4]", "Р[6-15.5-3]", "Р[6-15.5-4]",
			"Р[6-15.6-3]", "Р[6-15.6-4]", "Р[6-15.7-3]", "Р[6-15.7-4]", "Р[6-15.8-3]", "Р[6-15.8-4]", "Р[6-15.9-3]",
			"Р[6-15.9-4]", "Р[6-15.10-3]", "Р[6-15.10-4]", "Р[6-17.1-3]", "Р[6-17.1-4]", "Р[6-17.2-3]", "Р[6-17.2-4]",
			"Р[6-17.3-3]", "Р[6-17.3-4]", "Р[6-17.4-3]", "Р[6-17.4-4]", "Р[6-17.5-3]", "Р[6-17.5-4]", "Р[6-17.6-3]",
			"Р[6-17.6-4]", "Р[6-17.7-3]", "Р[6-17.7-4]", "Р[6-17.8-3]", "Р[6-17.8-4]", "Р[6-17.9-3]", "Р[6-17.9-4]",
			"Р[6-17.10-3]", "Р[6-17.10-4]");
	private static final List<String> REC_ARTICLE = Arrays.asList("Р[1-13-1]", "Р[1-13-2]", "Р[1-13.1-1]", "Р[1-13.1-2]", "Р[1-13.1-3]",
			"Р[2-21.1-1]", "Р[2-21.1-2]", "Р[2-21.2-1]", "Р[2-21.2-2]", "Р[2-21.3-1]", "Р[2-21.3-2]", "Р[2-21.4-1]", "Р[2-21.4-2]",
			"Р[2-21.5-1]", "Р[2-21.5-2]", "Р[2-21.6-1]", "Р[2-21.6-2]", "Р[4-6-1]", "Р[4-6-2]",
			"Р[5-7.1-1]", "Р[5-7.1-2]", "Р[5-7.2-1]", "Р[5-7.2-2]", "Р[5-7.3-1]", "Р[5-7.3-2]","Р[6-15.1-3]", "Р[6-15.1-4]", "Р[6-15.2-3]",
			"Р[6-15.2-4]", "Р[6-15.3-3]", "Р[6-15.3-4]", "Р[6-15.4-3]", "Р[6-15.4-4]", "Р[6-15.5-3]", "Р[6-15.5-4]",
			"Р[6-15.6-3]", "Р[6-15.6-4]", "Р[6-15.7-3]", "Р[6-15.7-4]", "Р[6-15.8-3]", "Р[6-15.8-4]", "Р[6-15.9-3]",
			"Р[6-15.9-4]", "Р[6-15.10-3]", "Р[6-15.10-4]", "Р[6-17.1-3]", "Р[6-17.1-4]", "Р[6-17.2-3]", "Р[6-17.2-4]",
			"Р[6-17.3-3]", "Р[6-17.3-4]", "Р[6-17.4-3]", "Р[6-17.4-4]", "Р[6-17.5-3]", "Р[6-17.5-4]", "Р[6-17.6-3]",
			"Р[6-17.6-4]", "Р[6-17.7-3]", "Р[6-17.7-4]", "Р[6-17.8-3]", "Р[6-17.8-4]", "Р[6-17.9-3]", "Р[6-17.9-4]",
			"Р[6-17.10-3]", "Р[6-17.10-4]");
	private static final List<String> REC_NUMUD = Arrays.asList("Р[1-3-1]","Р[1-3-2]","Р[1-3-3]","Р[1-3.3]","Р[1-3.3-1]","Р[1-3.3-2]",
			"Р[1-3.3-3]","Р[1-3.3-4]","Р[3-3-1]","Р[3-3-2]","Р[3-3.3]","Р[3-3.3-1]","Р[3-3.3-2]","Р[3-3.3-3]","Р[3-3.3-4]","Р[3-8-1]",
			"Р[3-8-2]","Р[3-8-3]","Р[3-8-1.1]","Р[3-8-1.2]","Р[3-8-1.3]","Р[3-8-1.4]","Р[2-3-1]","Р[2-3-2]","Р[2-3-3]","Р[2-3.3]",
			"Р[2-3.3-1]","Р[2-3.3-2]","Р[2-3.3-3]","Р[4-3-1]","Р[4-3-2]","Р[4-3-3]","Р[4-3.1]","Р[4-3.3]","Р[4-3.3-1]","Р[4-3.3-2]",
			"Р[4-3.3-3]","Р[4-3.3-4]","Р[5-3-1]","Р[5-3-2]","Р[5-3-3]","Р[5-3.3]","Р[5-3.3-1]","Р[5-3.3-2]","Р[5-3.3-3]","Р[5-3.3-4]",
			"Р[6-2]","Р[6-2.1]","Р[6-2.1-1]","Р[6-2.1-2]","Р[6-2.1-3]","Р[6-2.1-4]");
	private static final List<String> REC_OLD_NUMUD = Arrays.asList("Р[1-3-1]", "Р[1-3-2]", "Р[1-3-3]", "Р[3-3-1]", "Р[3-3-2]", "Р[3-8-2]", "Р[3-8-3]");
	private static final List<String> REC_SERVICE = Arrays.asList("Р[1-9]", "Р[1.1-34]", "Р[1.2-14]");
	private static final List<String> ARTICLE = Arrays.asList("171111","193011","212011","282111","282211","293011", "205111");
//	private static final List<String> REC_DAMAGE = Arrays.asList(a)


    public boolean isForm3(String rec) {
    	List<RecMiss> list = mapForms.get("F3").getRecMiss();

    	for (int i=0; i<list.size(); i++) {
    		if (rec.equals(list.get(i).getRecmost())) return true;
    	}
    	return false;
    	/*Optional<String> result = mapForms.entrySet()
    	            .stream()
    	            .filter(entry -> rec.equals(entry.getValue().form))
    	            .map(Map.Entry::getKey)
    	            .findFirst();

    	if (result.isPresent())
    	    return true;

    	return false;*/
    }


    /**
     * Изменяем реквизит в соответствии с настройками
     * @param recSet - настройки для реквизита в базе мск
     * @param val - значение реквизита из базы
     * @param rec - реквизит из МИССИС
     * @return реквизит в соответствии с настройками
     */
    public String ModifRec(Setting recSet, RecMiss rec, String val) {

		String value = val;
		boolean multi = isMulti(rec);

//			System.out.println(recSet.getRecmost());

		int ln = recSet.getLen()*recSet.getCount(); //Длинна реквизита в мосту
		if (value != null) {
			if (recSet.getRecmost().equals("Р[1-12-1]")) value=ReorgDate(value);
			if (recSet.getRecmost().equals("Р[5-4-1]")) value=value.substring(0, rec.getByte_()+recSet.getLen()-2);

			if (!recSet.getType().equals("R")&&multi) value = ReorgMulti(recSet,rec,value);
			else if (isNeedToConvert(rec.getRecmost())) value = ReorgRec(recSet,rec,value);
			else if (isNumUD(rec.getRecmost())) value = NumUD(recSet, rec, value);
			else if (isService(rec.getRecmost())) value = ReorgService(value);

			switch (recSet.getType()){
				case "R":{
					value = Strings.padEnd(value, ln, ' ');
					break;
				}
				default:{
					//Если длина реквизита меньше,чем байт с которого обрезаем, то дописыываем в конец нули
					if (value.length()<(rec.getByte_()-1)) value = Strings.padEnd(value, (rec.getByte_()-1), '0');
					if (!multi && value.length()!=recSet.getLen()) {
						value = value.substring(rec.getByte_()-1,
								(rec.getByte_()+recSet.getLen()) > value.length() ? value.length()
										: rec.getByte_()+recSet.getLen()-1);
					} else if (!multi && value.length()==recSet.getLen() && !recSet.getType().equals("D")) {
						value = value.substring(rec.getByte_()-1,
								(rec.getByte_()+recSet.getLen()) > value.length() ? value.length()
										: rec.getByte_()+recSet.getLen()-1);
					}
					value = Strings.padStart(value, ln, '0');
				}

			}
		} else if(isArticle(rec.getRecmost())) {
			value = Article(recSet, rec, "");
		}
		else {
			value = Strings.padStart("", ln, '0');
		}
//============================================
//		System.out.println(value + " | " + (value.length()==ln?true:false));

		return value;
	}

    public boolean isService(String rec) {
    	return (REC_SERVICE.contains(rec) ? true: false);
    }

    public String ReorgService(String val) {
    	String newRec = val;

    	if (val.equals("0026") || val.equals("0206")) newRec = "0006";

    	return newRec;
    }

    public String ReorgMulti(Setting recSet,RecMiss rec, String val) {

		String newRec = "";
		String[] recArr = val.split(",");

		for (int i = 0; i<recArr.length; i++) {

			if (recSet.getType().equals("E")) {
//				System.out.print(recSet.getRecmost() +": " + recArr[i]);
				recArr[i]=recArr[i].replaceAll("\\D", "");
//				System.out.println(" -> "+recArr[i] + "|");
			}
			if (recArr[i].substring(rec.getByte_()-1).length()>recSet.getLen()) recArr[i] = recArr[i].substring(rec.getByte_()-1, recSet.getLen());
			else if (recArr[i].substring(rec.getByte_()-1).length()<recSet.getLen()) recArr[i] = Strings.padStart(recArr[i].substring(rec.getByte_()-1), recSet.getLen(), '0');



			newRec += recArr[i];
		}
		return Strings.padEnd(newRec, recSet.getLen()*recSet.getCount(), '0');
	}

    /*public boolean isDamage(String rec) {
    	return (REC_NEED_CONVERT.contains(rec) ? true: false);
    }*/

    public boolean isNeedToConvert(String rec) {
		return (REC_NEED_CONVERT.contains(rec) ? true: false);
	}

    public boolean isMulti(RecMiss rec) {
		return ((rec.getNfile()!=1)&&(!isNeedToConvert(rec.getRecmost())) ? true: false);
	}

    public String R030(String recmost, String val) {
		String newRec = "";

		String zn = Strings.padStart(recmost.substring(recmost.indexOf(".")+1,recmost.length()-1), 2, '0');

		String[] recArr = val.split(",");

		for (int i=0; i<recArr.length; i++) {
			if (recArr[i].substring(0, 2).equals(zn)) {
				newRec = recArr[i].substring(2);
				break;
			}
		}

		return newRec;
	}

	/**
	 * Проверяем является ли реквизит статьей
	 * @param rec
	 * @return true - является, false - нет
	 */
    public boolean isArticle(String rec) {
		return (REC_ARTICLE.contains(rec) ? true: false);
	}

    public String Article(Setting st, RecMiss rec, String val) {
		String newRec = "";

		switch (st.getType()) {
			case "I":{
				if (val!="") newRec = val.substring(rec.getByte_()-1, st.getLen());
				newRec = Strings.padEnd(newRec, st.getLen()*st.getCount(), '0');
				break;
			}
			case "R":{
				if (val!="") newRec = val.substring(rec.getByte_()-1);
				if (ARTICLE.contains(val)) {
					if (val.equals("171111")) newRec = "7";
					else newRec="6";
				}
				newRec = Strings.padEnd(newRec.replace("0", ""), st.getLen()*st.getCount(), ' ');

				break;
			} default: {
				if (val!="") newRec = Strings.insert(val, 3, '0');
				else newRec = Strings.padEnd(newRec, st.getLen()*st.getCount(), '0');
			}
		}
		return newRec;
	}

    public boolean isNumUD(String rec) {
    	return (REC_NUMUD.contains(rec) ? true: false);
    }

    public String NumUD(Setting st, RecMiss rec, String val) {
    	String newRec="";

    	if (val.length()<17) val = Strings.padEnd(val, 17, '0');

    	if (REC_OLD_NUMUD.contains(st.getRecmost()) && !val.substring(12,17).equals("00000")) {
    		if (st.getRecmost().equals("Р[3-8-2]")||st.getRecmost().equals("Р[3-8-3]") ) {
    			if (Strings.isDigit(val.substring(0,3))) {
	    			if (Integer.parseInt(val.substring(0,3))>116) {
	    				newRec = val.substring(0, 1) + "20" + val.substring(1, 3) + val.substring(8);
	    			} else {
	    				newRec = val.substring(0, 1) + "20" + val.substring(2, 4) + val.substring(9);
	    			}
    			}
    		}else {
    			newRec = Strings.padEnd(newRec, st.getLen()*st.getCount(), '0');
    		}
    	} else if (REC_OLD_NUMUD.contains(st.getRecmost()) && val.substring(12,17).equals("00000")){
    		if ( (st.getRecmost().equals("Р[3-8-2]")||st.getRecmost().equals("Р[3-8-3]")) &&
    				Strings.isDigit(val.substring(0,3)) &&
    				(Integer.parseInt(val.substring(0,3))<118) ) {

    				val = val.substring(1);
    		}
    		newRec = Strings.insert(val, 5, "00");
    	} else  if (!REC_OLD_NUMUD.contains(st.getRecmost()) && val.substring(12,17).equals("00000")) {
    		//Изменять в соответствии с новыми реквизитами
    		String yy = "";
    		if (val.substring(1,4).equals("2000")) yy="50";
    		else yy=val.substring(3, 5);
    		newRec = val.substring(0, 1) + yy + "0014000" + val.substring(5, 12);
    	} else newRec = val;

    	newRec.replace('К', '9');
    	newRec.replace('П', '6');

    	return newRec;
    }

    public String ReorgDate(String val) {
    	String s = val;
    	val = Strings.padEnd(val, 8, '0');
    	s= val.substring(0, 4);
    	if (val.substring(4,6).equals("00"))
    		s+="01";
    	else s += val.substring(4,6);
    	if (val.substring(6,8).equals("00"))
    		s+="01";
    	else s += val.substring(6,8);
    	return s;
    }

    public String ReorgRec(Setting recSet, RecMiss rec, String oldVal) {
		String newRec = "";

		if (isArticle(recSet.getRecmost())) {
			newRec = Article(recSet, rec, oldVal);
		} else

			switch (rec.getRecmis()) {
				/*case "013":{
					newRec = Article(recSet, rec, oldVal);
					break;
				}
				case "021":{
					newRec = Article(recSet, rec, oldVal);
					break;
				}
				case "222":{
					newRec = Article(recSet, rec, oldVal);
					break;
				}
				case "223":{
					newRec = Article(recSet, rec, oldVal);
					break;
				}
				case "007":{
					newRec = Article(recSet, rec, oldVal);
					break;
				}
				case "711":{
					newRec = Article(recSet, rec, oldVal);
					break;
				}
				case "712":{
					newRec = Article(recSet, rec, oldVal);
					break;
				}
				case "311":{
					newRec = Article(recSet, rec, oldVal);
					break;
				}*/
				case "030":{
					newRec = R030(recSet.getRecmost(),oldVal);
					newRec = Strings.padEnd(newRec, recSet.getLen()*recSet.getCount(), '0');
	//				newRec = String.format("%0" + recSet.getLen() + "d", 0);
					break;
				}
				case "005":{
					if (recSet.getType().equals("D")) {
						newRec = oldVal.substring(rec.getByte_()-1);
					} else {
						newRec = String.format("%s", oldVal.substring(1, 6));
					}
					break;
				}
		}

		switch (rec.getRecmost()) {
		case "Р[3-2]":{
			if (oldVal.substring(0,1).equals("4")) newRec = "1";
			else newRec=oldVal;
			break;
		}
		}

		return newRec;
	}
}
