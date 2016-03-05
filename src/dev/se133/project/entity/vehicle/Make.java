package dev.se133.project.entity.vehicle;

/**
 * Describes a vehicle manufacturer.
 */
@SuppressWarnings("javadoc")
public enum Make {
	ABARTH("Abarth"),
	ALFAROMEO("Alfa Romeo"),
	ASTONMARTIN("Aston Martin"),
	AUSTIN("Austin"),
	AUDI("Audi"),
	
	BEDFORD("Bedford"),
	BENTLEY("Bentley"),
	BMW("BMW"),
	BOLWELL("Bolwell"),
	BUFORI("Bufori"),
	
	CADILLAC("Cadillac"),
	CATERHAM("Caterham"),
	CHERY("Chery"),
	CHEVROLET("Chevrolet"),
	CHRYSLER("Chrysler"),
	CITROEN("Citroen"),
	
	DAEWOO("Daewoo"),
	DAIHATSU("Daihatsu"),
	DODGE("Dodge"),
	
	FIAT("Fiat"),
	FORD("Ford"),
	
	GEELY("Geely"),
	GREATWALL("Great Wall"),
	
	HINO("Hino"),
	HONDA("Honda"),
	HUMMER("Hummer"),
	HYUNDAI("Hyundai"),
	
	INFINITI("Infiniti"),
	ISUZU("Isuzu"),
	JAGUAR("Jaguar"),
	JEEP("Jeep"),
	
	KIA("KIA"),
	
	LANDROVER("Land Rover"),
	LEXUS("Lexus"),
	LOTUS("Lotus"),
	
	MAZDA("Mazda"),
	MERCEDESBENZ("Mercedes-Benz"),
	MINI("Mini"),
	MITSUBISHI("Mitsubishi"),
	
	NISSAN("Nissan"),
	
	OPEL("Opel"),
	
	PORSCHE("Porsche"),
	PROTON("Proton"),
	
	RANGEROVER("Range Rover"),
	RENAULT("Renault"),
	
	SAAB("Saab"),
	SKODA("Skoda"),
	SSANGYONG("Ssangyong"),
	SUBARU("Subaru"),
	SUZUKI("Suzuki"),
	
	TAT("Tata"),
	TOYOTA("Toyota"),
	
	VOLKSWAGEN("Volkswagen"),
	VOLVO("Volvo");
	
	private String name;
	
	private Make(String name) {
		this.name= name;
	}
	
	/** @return manufacturer name */
	public String getName() {
		return name;
	}
}
