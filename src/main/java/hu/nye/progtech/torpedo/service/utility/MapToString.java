package hu.nye.progtech.torpedo.service.utility;

import hu.nye.progtech.torpedo.model.MapVO;

public class MapToString {
    public String convertMapVoMapToString(MapVO mapVO) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < mapVO.getNumberOfRows(); i++) {
            for (int j = 0; j < mapVO.getNumberOfColumns(); j++) {
                char character = mapVO.getMap()[i][j];
                builder.append(character);
            }
            builder.append("\n");
        }

        return builder.toString();
    }

    public String convertMapVoShootableToString(MapVO mapVO) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < mapVO.getNumberOfRows(); i++) {
            for (int j = 0; j < mapVO.getNumberOfColumns(); j++) {
                boolean shootableValue = mapVO.getIsShootable()[i][j];
                String shootableValueAsString = shootableValue ? "1" : "0";
                builder.append(shootableValueAsString);
            }
            builder.append("\n");
        }

        return builder.toString();
    }
}
