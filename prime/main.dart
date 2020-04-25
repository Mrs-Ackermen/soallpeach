import 'dart:io';

import 'dart:math';

void main(List<String> args){
  if(args.length == 0){
    print("file name is not exist!");
    return;
  }

  List input = readFileAsync(args[0]).split("\n");
  
  input.forEach((s){
    int k = isPrime(s);
    if(k >= 0 )
      print("$k");
  });
}

String readFileAsync(String fileName){
  File file = new File(fileName);
  return file.readAsStringSync();
}

int isPrime(String s){
  int num = double.parse(s, (e) => -1).toInt();
  if(num < 1){
    return 0;
  }

  for(int i=2 ; i< sqrt(num); i++){
    if(num % i == 0)
      return 0;
  }

  return 1;
}