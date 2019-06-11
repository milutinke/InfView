package rs.raf.actions;

import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.AbstractAction;
import javax.swing.JCheckBox;

import rs.raf.model.Entity;

import rs.raf.model.UIAbstractFile;

import rs.raf.view.frames.MainFrame;
import rs.raf.view.panels.DefineBinarySearchView;

@SuppressWarnings("serial")
public class BinarySearchAction extends AbstractAction {
	DefineBinarySearchView view;
	UIAbstractFile sekFile;

	public BinarySearchAction(DefineBinarySearchView view, UIAbstractFile sekFile) {
		this.view = view;
		this.sekFile = sekFile;
	}

	@SuppressWarnings({ "static-access", "unused" })
	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			String fileName = ((Entity) view.getEntity()).getFile().replaceAll(".sek", ".stxt").trim();
			String location = ((Entity) view.getEntity()).getLocation();

			@SuppressWarnings("resource")
			RandomAccessFile file = new RandomAccessFile(location + fileName, "r");

			String line = file.readLine();

			long lineLength = line.length() + 2;
			long numberOfLines = (file.length() + 2) / lineLength;

			int startingPosition = 0;
			long start = 0;
			long end = numberOfLines;

			file.close();

			long mid;
			boolean check = false;

			System.out.println("File: " + location + fileName);
			file = new RandomAccessFile(location + fileName, "r");
			int positionOfFound;

			while (true) {
				positionOfFound = -1;
				check = true;
				startingPosition = 0;

				if (end - start == 1)
					start = end;

				mid = (start + end) / 2;

				file.seek((mid - 1) * lineLength);

				line = file.readLine();

				positionOfFound++;

				boolean isFound = false;
				for (JCheckBox checkBox : view.getCheckBoxes()) {
					check = true;

					int index = view.getCheckBoxes().indexOf(checkBox);
					int length = view.getLengths().get(index);

					if (checkBox.isSelected()) {
						String requestedText = view.getTextFields().get(index).getText();

						int x;
						for (x = startingPosition; x < length; x++) {
							if (line.charAt(x) == ' ')
								break;
						}

						String line2 = line.substring(startingPosition, x);

						if (requestedText.equals(line2)) {
							isFound = true;
						} else {
							isFound = false;
							int i = 0;

							int firstNumber = 0;
							int secondNumber = 0;

							Character firstCharacter = null;
							Character secondCharacter = null;

							if (requestedText.length() != line2.length()) {
								if (requestedText.length() > line2.length())
									start = mid;
								else
									end = mid;
							} else {
								while (i < requestedText.length()) {
									firstCharacter = requestedText.charAt(i);
									secondCharacter = line2.charAt(i);

									if (firstCharacter.isAlphabetic(firstCharacter))
										firstCharacter = firstCharacter.toUpperCase(firstCharacter);
									if (secondCharacter.isAlphabetic(secondCharacter))
										secondCharacter = secondCharacter.toUpperCase(secondCharacter);

									if (firstCharacter < secondCharacter) {
										end = mid;
										check = false;
										break;
									} else if (secondCharacter < firstCharacter) {
										start = mid;
										check = false;
										break;
									} else if (firstCharacter.isAlphabetic(firstCharacter)
											&& secondCharacter.isAlphabetic(secondCharacter)) {
										check = true;
										break;
									} else {
										i++;
									}
								}

								if (check == false)
									break;
								else {
									startingPosition += length;
									continue;
								}
							}

						}
					}

					startingPosition += length;
				}

				if (isFound) {
					int k = 0;
					break;
				}

				if (start == end) {
					MainFrame.getInstance().getExceptionsManager().errorNoResoults();
					break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Search is over");
	}

}