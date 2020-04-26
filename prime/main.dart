import 'dart:typed_data';
import 'dart:math';
import 'dart:io';


Iterable<int> soeOdds(int limit) {
  if (limit < 3) return limit < 2 ? Iterable.empty() : [2];
  int lmti = (limit - 3) >> 1;
  int bfsz = (lmti >> 3) + 1;
  int sqrtlmt = (sqrt(limit) - 3).floor() >> 1;
  Uint32List cmpsts = Uint32List(bfsz);
  for (int i = 0; i <= sqrtlmt; ++i)
    if ((cmpsts[i >> 5] & (1 << (i & 31))) == 0) {
      int p = i + i + 3;
      for (int j = (p * p - 3) >> 1; j <= lmti; j += p)
        cmpsts[j >> 5] |= 1 << (j & 31);
    }
  return
    [2].followedBy(
      Iterable.generate(lmti + 1)
      .where((i) => cmpsts[i >> 5] & (1 << (i & 31)) == 0)
      .map((i) => i + i + 3) );
}
 
final int range = 50000;

void main(List<String> args) { 
  if(args.length == 0){
    print("file name is not exist!");
    return;
  }

  List input = readFileAsync(args[0]).split("\n");
  Iterable<int> primeList = soeOdds(range);

  input.forEach((s){
    int number = int.parse(s);
    if (number < range) {
      if(primeList.contains(number))
        print("1");
      else 
        print("0");
    } else {
        int x = 1; 
        int y = sqrt(number).toInt() + 1;
        for (int z=5; z<y; z+=6)
            if (number % z == 0 || number % (z+2) == 0) {
                x = 0;
                break;
            }
        print(x);
    }
  });
}

String readFileAsync(String fileName){
  File file = new File(fileName);
  return file.readAsStringSync();
}
