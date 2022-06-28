# 팩토리 메소드 (Factory method) 패턴
구체적으로 어떤 인스턴스를 만들지는 서브 클래스가 정한다.
- 다양한 구현체 (Product)가 있고, 그 중에서 특정한 구현체를 만들 수 있는 다양한 팩토리 
(Creator)를 제공할 수 있다.

- 확장에 열려있고 변경에 닫혀있는 구조로 변경 (_02_factorymethod/before -> _02_factorymethod/after_v1)
- but, 아직 클라이언트 코드는 변경에 열려 있음