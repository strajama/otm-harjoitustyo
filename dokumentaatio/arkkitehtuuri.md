<?xml version="1.0" encoding="UTF-8"?>

<ClassDiagram name="Multi-file class diagram">
  <ClassDiagramComponents>
    <Class name="Action" package="seikkailupeli.domain" visibility="public" xPosition="12.0" yPosition="191.0">
      <Fields/>
      <Constructors/>
      <Methods/>
    </Class>
    <Class name="Adventure" package="seikkailupeli.domain" visibility="public" xPosition="30.0" yPosition="425.0">
      <Fields>
        <Field name="world" type="World" visibility="private"/>
      </Fields>
      <Constructors/>
      <Methods/>
    </Class>
    <Class name="Area" package="seikkailupeli.domain" visibility="public" xPosition="884.0" yPosition="448.0">
      <Fields>
        <Field name="name" type="String" visibility="private"/>
        <Field name="description" type="String" visibility="private"/>
        <Field name="items" type="Map&lt;String, Item&gt;" visibility="private"/>
        <Field name="location" type="Location" visibility="private"/>
      </Fields>
      <Constructors/>
      <Methods/>
    </Class>
    <Class name="AreaDao" package="seikkailupeli.dao" visibility="public" xPosition="680.0" yPosition="695.0">
      <Fields>
        <Field name="database" type="Database" visibility="private"/>
      </Fields>
      <Constructors/>
      <Methods/>
    </Class>
    <Class name="Database" package="seikkailupeli.dao" visibility="public" xPosition="400.0" yPosition="695.0">
      <Fields>
        <Field name="databaseAddress" type="String" visibility="private"/>
      </Fields>
      <Constructors/>
      <Methods/>
    </Class>
    <Class name="Item" package="seikkailupeli.domain" visibility="public" xPosition="796.0" yPosition="38.0">
      <Fields>
        <Field name="name" type="String" visibility="private"/>
        <Field name="description" type="String" visibility="private"/>
        <Field name="area" type="Area" visibility="private"/>
      </Fields>
      <Constructors/>
      <Methods/>
    </Class>
    <Class name="ItemDao" package="seikkailupeli.dao" visibility="public" xPosition="604.0" yPosition="465.0">
      <Fields>
        <Field name="database" type="Database" visibility="private"/>
      </Fields>
      <Constructors/>
      <Methods/>
    </Class>
    <Class name="Location" package="seikkailupeli.domain" visibility="public" xPosition="1074.0" yPosition="270.0">
      <Fields>
        <Field name="i" type="int" visibility="private"/>
        <Field name="j" type="int" visibility="private"/>
      </Fields>
      <Constructors/>
      <Methods/>
    </Class>
    <Class name="Player" package="seikkailupeli.domain" visibility="public" xPosition="312.0" yPosition="140.0">
      <Fields>
        <Field name="area" type="Area" visibility="private"/>
        <Field name="items" type="Map&lt;String, Item&gt;" visibility="private"/>
      </Fields>
      <Constructors/>
      <Methods/>
    </Class>
    <Class name="SeikkailuFXMain" package="seikkailupeli.ui" visibility="public" xPosition="154.0" yPosition="724.0">
      <Fields/>
      <Constructors/>
      <Methods/>
    </Class>
    <Class name="World" package="seikkailupeli.domain" visibility="public" xPosition="227.0" yPosition="448.0">
      <Fields>
        <Field name="areas" type="List&lt;Area&gt;" visibility="private"/>
        <Field name="items" type="List&lt;Item&gt;" visibility="private"/>
        <Field name="grid" type="Area[][]" visibility="private"/>
        <Field name="player" type="Player" visibility="private"/>
        <Field name="areaDao" type="AreaDao" visibility="private"/>
        <Field name="itemDao" type="ItemDao" visibility="private"/>
        <Field name="database" type="Database" visibility="private"/>
      </Fields>
      <Constructors/>
      <Methods/>
    </Class>
  </ClassDiagramComponents>
  <ClassDiagramRelations>
    <HasRelation name="database" source="seikkailupeli.domain.World" target="seikkailupeli.dao.Database" type="Aggregation" sourceCardinality="1..1" targetCardinality="1..1"/>
    <HasRelation name="items" source="seikkailupeli.domain.Area" target="seikkailupeli.domain.Item" type="Aggregation" sourceCardinality="1..1" targetCardinality="0..*" collectionType="Map&lt;String, Item&gt;"/>
    <UseRelation source="seikkailupeli.dao.ItemDao" target="seikkailupeli.domain.Item" sourceCardinality="1..1" targetCardinality="1..1"/>
    <HasRelation name="items" source="seikkailupeli.domain.World" target="seikkailupeli.domain.Item" type="Aggregation" sourceCardinality="1..1" targetCardinality="0..*" collectionType="List&lt;Item&gt;"/>
    <HasRelation name="location" source="seikkailupeli.domain.Area" target="seikkailupeli.domain.Location" type="Aggregation" sourceCardinality="1..1" targetCardinality="1..1"/>
    <HasRelation name="player" source="seikkailupeli.domain.World" target="seikkailupeli.domain.Player" type="Aggregation" sourceCardinality="1..1" targetCardinality="1..1"/>
    <UseRelation source="seikkailupeli.domain.Action" target="seikkailupeli.domain.World" sourceCardinality="0..*" targetCardinality="1..1"/>
    <UseRelation source="seikkailupeli.dao.AreaDao" target="seikkailupeli.domain.Area" sourceCardinality="1..1" targetCardinality="1..1"/>
    <HasRelation name="itemDao" source="seikkailupeli.domain.World" target="seikkailupeli.dao.ItemDao" type="Aggregation" sourceCardinality="1..1" targetCardinality="1..1"/>
    <HasRelation name="database" source="seikkailupeli.dao.ItemDao" target="seikkailupeli.dao.Database" type="Aggregation" sourceCardinality="1..1" targetCardinality="1..1"/>
    <UseRelation name="" source="seikkailupeli.domain.Action" target="seikkailupeli.domain.Player" sourceCardinality="0..*" targetCardinality="1..1"/>
    <HasRelation name="world" source="seikkailupeli.domain.Adventure" target="seikkailupeli.domain.World" type="Aggregation" sourceCardinality="1..1" targetCardinality="1..1"/>
    <HasRelation name="areas" source="seikkailupeli.domain.World" target="seikkailupeli.domain.Area" type="Aggregation" sourceCardinality="1..1" targetCardinality="0..*" collectionType="List&lt;Area&gt;"/>
    <HasRelation name="area" source="seikkailupeli.domain.Player" target="seikkailupeli.domain.Area" type="Aggregation" sourceCardinality="1..1" targetCardinality="1..1"/>
    <HasRelation name="items" source="seikkailupeli.domain.Player" target="seikkailupeli.domain.Item" type="Aggregation" sourceCardinality="1..1" targetCardinality="0..*" collectionType="Map&lt;String, Item&gt;"/>
    <UseRelation name="" source="seikkailupeli.ui.SeikkailuFXMain" target="seikkailupeli.domain.World" sourceCardinality="1..1" targetCardinality="1..1"/>
    <HasRelation name="database" source="seikkailupeli.dao.AreaDao" target="seikkailupeli.dao.Database" type="Aggregation" sourceCardinality="1..1" targetCardinality="1..1"/>
    <HasRelation name="areaDao" source="seikkailupeli.domain.World" target="seikkailupeli.dao.AreaDao" type="Aggregation" sourceCardinality="1..1" targetCardinality="1..1"/>
    <HasRelation name="area" source="seikkailupeli.domain.Item" target="seikkailupeli.domain.Area" type="Aggregation" sourceCardinality="1..1" targetCardinality="1..1"/>
  </ClassDiagramRelations>
</ClassDiagram>
