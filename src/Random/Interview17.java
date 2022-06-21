
package Random;

import java.util.Arrays;
import java.util.HashMap;

public class Interview17 {

    public String[] trulyMostPopular(String[] names, String[] synonyms) {
        HashMap<String, Integer> map = new HashMap<>();
        int []nums = new int[names.length];
        String []namelist = new String[names.length];
        int index = 0;
        for(String name: names){
            int i = name.indexOf('(');
            String curr = name.substring(0,i);
            Integer num = Integer.valueOf(name.substring(i + 1, name.length()-1));
            map.put(curr, index);
            namelist[index] = curr;
            nums[index] = num;
            index++;
        }
        Union union = new Union(nums, names.length, namelist);

        for(String s : synonyms){
            int in = s.indexOf(',');
            String name1 = s.substring(1,in);
            String name2 = s.substring(in+1,s.length()-1);
            if(!map.containsKey(name1)){
                map.put(name1,index++);
            }
            if(!map.containsKey(name2)){
                map.put(name2,index++);
            }
        }

        for(String sub: synonyms){
            int j = sub.indexOf(',');
            String first = sub.substring(1,j);
            String second = sub.substring(j + 1, sub.length() -1);
            if(map.containsKey(first) && map.containsKey(second)){
                union.union(map.get(first), map.get(second));
            }
        }

        return union.trulyMostPopular();


    }

    public static void main(String[] args) {
        String[] input1 = {"Wunqax(96)", "Uzpk(82)", "Osh(35)", "Jtnh(18)", "Itzh(44)", "Uuhkq(55)", "Qdnelp(55)", "Wzjk(91)", "Ztapky(33)", "Ybzvzu(46)", "Imfn(72)", "Xrzs(71)", "Xcqh(61)", "Ydh(65)", "Wpwy(63)", "Usb(62)", "Nhfrbb(97)", "Wwhnyb(41)", "Amdz(52)", "Vtuwh(75)", "Qus(81)", "Jsgaug(47)", "Ophzz(75)", "Affzwm(1)", "Qvogv(84)", "Wybk(77)", "Swlia(52)", "Evtun(69)", "Isc(89)", "Uelrb(6)", "Ctlwjd(54)", "Shtvck(1)", "Emz(11)", "Sbh(24)", "Otacsp(96)", "Ejue(15)", "Hhw(60)", "Saou(61)", "Lrbba(51)", "Kiz(92)", "Szmnvu(14)", "Ejrxje(88)", "Ndhnm(23)", "Jsa(93)", "Cggsqc(89)", "Jlobv(21)", "Ejljt(62)", "Wyi(3)", "Anbt(71)", "Vrdmy(61)", "Clw(26)", "Oaimq(2)", "Yjeu(98)", "Jsbzzl(38)", "Mnihs(77)", "Qpizf(34)", "Ayaj(36)", "Gesl(26)", "Vzo(30)", "Wxait(93)", "Jze(20)", "Ajkgnh(8)", "Hka(31)", "Pbz(83)", "Wtnsu(89)", "Afkbg(54)", "Nkw(95)", "Bbz(65)", "Oqbk(24)", "Shl(97)", "Ylf(56)", "Ytg(29)", "Tusg(50)", "Fxhtzx(8)", "Tleub(17)", "Yin(66)", "Abhm(82)", "Eql(58)", "Vuvje(38)", "Nmr(31)", "Ykhhar(83)", "Taiin(79)", "Qap(5)", "Inrqp(50)", "Uyjfmh(28)", "Lmzsfi(55)", "Obk(24)", "Iml(9)", "Fapu(67)", "Bhubwi(6)", "Qdazt(77)", "Pmvbst(44)", "Krc(12)", "Jsowzy(79)", "Eksnn(2)", "Kqurnj(48)", "Phu(5)", "Gndk(96)", "Zruayf(3)", "Zwdtz(28)"};
        String[] input2 = {"(Wwhnyb,Jsowzy)", "(Ejue,Tleub)", "(Ybzvzu,Obk)", "(Qpizf,Wxait)", "(Mnihs,Shl)", "(Oqbk,Shl)", "(Osh,Gndk)", "(Ztapky,Fapu)", "(Wybk,Jsa)", "(Qus,Emz)", "(Ydh,Cggsqc)", "(Usb,Ejljt)", "(Jtnh,Iml)", "(Qpizf,Zwdtz)", "(Ykhhar,Gndk)", "(Ctlwjd,Sbh)", "(Jtnh,Jze)", "(Sbh,Saou)", "(Jtnh,Ejue)", "(Nkw,Ylf)", "(Uelrb,Ajkgnh)", "(Qvogv,Tusg)", "(Sbh,Shl)", "(Qvogv,Vrdmy)", "(Eksnn,Zruayf)", "(Xrzs,Pmvbst)", "(Ayaj,Vuvje)", "(Nhfrbb,Anbt)", "(Wpwy,Eksnn)", "(Gesl,Tusg)", "(Qvogv,Ayaj)", "(Itzh,Saou)", "(Jlobv,Tusg)", "(Wpwy,Oaimq)", "(Imfn,Eksnn)", "(Vtuwh,Tusg)", "(Ayaj,Pmvbst)", "(Abhm,Uyjfmh)", "(Swlia,Lmzsfi)", "(Uuhkq,Shtvck)"};
        System.out.println(Arrays.toString(new Interview17().trulyMostPopular(input1, input2)));
    }

}

class Union{
    int count;
    int []parent;
    int []size;
    String []names;
    int []nums;
    Union(int []nums, int n, String []names){
        parent = new int[n];
        size = new int[n];
        this.names = names;
        this.nums = nums;
        for(int i = 0; i < n; i++){
            parent[i] = i;
            size[i] = 1;
        }
        count = n;
    }

    public int find(int x){
        if(parent[x] != x){
            parent[x] = parent[parent[x]];
            x = parent[x];
        }

        return x;
    }

    public void union(int p, int q){
        int rootP = find(p);
        int rootQ = find(q);
        if(rootP == rootQ) return;
        if(names[rootP].compareTo(names[rootQ]) > 0){
            parent[rootP] = rootQ;
            size[rootQ] += rootP;
            nums[rootQ] += nums[rootP];
        }else{
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
            nums[rootP] += nums[rootQ];
        }
        count--;
    }

    public String[] trulyMostPopular(){
        String []res = new String[count];
        int index = 0;
        for(int i = 0; i < nums.length; i++){
            if(parent[i] == i){
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(names[i]);
                stringBuilder.append("(");
                stringBuilder.append(nums[i]);
                stringBuilder.append(")");
                res[index++] = stringBuilder.toString();
            }
        }
        Arrays.sort(res);
        return res;
    }


}