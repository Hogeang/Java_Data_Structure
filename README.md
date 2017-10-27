# **Data Structure**

기본적인 Data Structure를 구현한 것으로 [JaeYeopHan님의 개발자 인터뷰](https://github.com/JaeYeopHan/Interview_Question_for_Beginner/tree/master/DataStructure)를 참조하여,

 Java에 맞게 구현한 Code 들입니다.

기본적인 이론은 JaeYeopHan님의 설명을 보시면 되겠습니다.

> List
>
> Stack
>
> Depth First Search - Tree Preorder
>
> Queue
>
> Breadth First Search - Tree Level Traversal
>
> Binary Tree ( 자식 Tree가 양쪽 삭제일시 미구현 )
>
> Priority Queue
>
> Hash function : divide
>
> Hash function : digits folding
>
> Hash function : Open Addressing : Linear Probing
>
> Hash function : Close Addressing : Bucket ( Collision 상황을 가정 )
>
> Graph : Adjceny Matrix
>
> Graph : Topological Sort



## List

Java 자체는 Pointer가 unsafe이며 사용자체를 하고 있지 않습니다. 

따라서 저는 기존의 배열보다 큰 동적 임시 배열을 만들어서,  기존 배열의 공간을 확장시킨 후 값을 넣습니다. 

```java
/* Insert Code 일부분 */
else if((Position == 1) && (Number>0)){
  String[] Temp = new String[this.Number]; // 기존의 배열 공간만큼 임시 배열을 만들어서,
  for (int i = 0; i < this.Number; i++)
      Temp[i] = this.Context[i]; // 기존의 값을 백업 시킨다.
      this.Number++; // 새로 넣을 배열 공간을 만든다.
      this.Context = new String[this.Number]; // 증가된 공간만큼 기존 배열을 새롭게 다시 만든다.
      this.Context[0] = Data; // 집어 넣어야 할 위치에 새로운 Data를 넣는다.
  for(int i=1;i<this.Number;i++) {
    this.Context[i] = "";  
    this.Context[i] = Temp[i - 1];
  } // 기존의 Data들을 다시 Context에 넣고 함수 결과를 끝낸다.

}

```

삭제시에는 기존 배열보다 작은 동적 임시 배열을 만들어서, 기존의 배열 공간을 축소시킨 후 처리했습니다.



```java
/* Delete Code 일부분 */      
else if((Position == 1) && (Number>1)){
  Number--; // 값을 삭제할 예정이므로 기존의 값보다 작게 만든다.
  String[] Temp = new String[this.Number]; // 임시로 값을 담을 배열을 하나 만든다.
    for (int i = 0; i < this.Number; i++)
      Temp[i] = this.Context[i+1]; // 삭제할 위치를 제외한 나머지 값을 임시 배열에 넣는다.
      this.Context = new String[Number];  // 삭제할 값을 제외한 공간만큼 다시 배열을 만듭니다.
  for(int i=0;i<this.Number;i++) {
      this.Context[i] = ""; 
      this.Context[i] = Temp[i];
    } // 임시로 백업해 두었던 값을 전부 원래 배열로 옮기고 함수 결과를 끝냅니다.

}
```



삭제, 삽입 , 검색 등의 기능을 While 문을 이용하여 확인합니다.



```java
 System.out.print("1 - 삽입 / 2 - 삭제 / 3 - 검색 / 4 - 길이 / 5 - 다시 생성 / 6 - 리스트 파괴 / 7 - 비어있는지 확인 / 8 - 모든 검색  ");
        Select = scanner.nextInt(); // 선택하여 기능을 선택.
        switch(Select){
            // 삽입 기능
            case 1:
                System.out.println("삽입 기능입니다.");
                System.out.print("해당 위치 :");
                Position = scanner.nextInt();
                scanner = new Scanner(System.in);
                System.out.print("해당 Data :");
                Data = scanner.nextLine();
                list.Insert(Position,Data);
            break;
            // 삭제 기능
            case 2:
                System.out.println("삭제 기능입니다.");
                scanner = new Scanner(System.in);
                System.out.print("삭제할 위치 :");
                Position = scanner.nextInt();
                list.Delete(Position);
            break;
            // 검색 기능
            case 3:
                scanner = new Scanner(System.in);
                System.out.print("검색할 위치 :");
                Position = scanner.nextInt();
                list.Retrieve(Position);
            break;
            // 길이 기능
            case 4:
                list.Length();
            break;
            case 5:
                list.Create();
            break;
            case 6:
                list.Destroy();
            break;
            case 7:
                list.Isempty();
            break;
            case 8:
                list.AllRetrieve();
            break;
        }

    }

```

